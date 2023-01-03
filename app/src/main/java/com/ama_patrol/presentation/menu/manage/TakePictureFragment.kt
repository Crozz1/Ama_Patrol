import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.ama_patrol.R
import com.ama_patrol.databinding.AmbilGambarBinding
import com.google.android.material.snackbar.Snackbar
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.resolution
import id.zelory.compressor.constraint.size
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class TakePictureFragment : Fragment() {

    private var _binding: AmbilGambarBinding? = null
    private val binding get() = _binding!!

    private var latestTmpUri: Uri? = null
    private var latestTmpFile: File? = null
    private var finalPath: String = ""

    private val permissions = mutableListOf(
        Manifest.permission.CAMERA
    )

    private fun allPermissionsGranted() = permissions.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private val contract =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                latestTmpFile?.let { file ->
                    lifecycleScope.launch {
                        val compressedFile = Compressor.compress(requireContext(), file) {
                            resolution(720, 1280) //resolusi gambar pada saat di kompress
                            size(1000 * 1024) //Ukuran maksimal gambar pada saat di kompres
                        }
                        finalPath = compressedFile.path
                        binding.imagePreview.load(finalPath)
                    }
                }
            }
        }

    private val permissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.all { it.value }) {
                getTmpFileUri().let { uri ->
                    latestTmpUri = uri
                    contract.launch(latestTmpUri)
                }
            } else {
                view?.let { v ->
                    Snackbar.make(v, R.string.message_no_permissions, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.ok) { ActivityCompat.finishAffinity(requireActivity()) }
                        .show()
                }
            }
        }

    private val selectImageFromGalleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { finalUri ->
                val bitmap = getBitmap(requireContext().contentResolver, finalUri)
                val file = File(requireContext().cacheDir, "image")
                if (!file.exists())
                    file.mkdirs()

                val finalImage = File(file, "shop_image${System.currentTimeMillis()}.jpeg")

                try {
                    val fos = FileOutputStream(finalImage)
                    bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                    fos.flush()
                    fos.close()
                } catch (exc: Exception) {
                    exc.printStackTrace()
                }

                if ((finalImage.length() / 1024) > (1000 * 1024)) {
                    lifecycleScope.launch {
                        val compressedFile = Compressor.compress(requireContext(), finalImage) {
                            resolution(720, 1280)
                            size(1000 * 1024)
                        }

                        finalPath = compressedFile.path
                        binding.imagePreview.load(finalPath)
                    }
                } else {
                    finalPath = finalImage.path
                    binding.imagePreview.load(finalPath)
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = AmbilGambarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            takeImageButton.setOnClickListener {
                if (allPermissionsGranted()) {
                    getTmpFileUri().let { uri ->
                        latestTmpUri = uri
                        contract.launch(latestTmpUri)
                    }
                } else {
                    permissionRequest.launch(permissions.toTypedArray())
                }
            }
            selectImageButton.setOnClickListener {
                selectImageFromGallery()
            }
        }
    }


    private fun selectImageFromGallery() = selectImageFromGalleryResult.launch("image/*")

    private fun getTmpFileUri(): Uri {
        val directory = File(requireContext().cacheDir, "image")
        if (!directory.exists())
            directory.mkdirs()

        val tmpFile =
            File.createTempFile("shop_image", ".jpeg", directory).apply {
                createNewFile()
                deleteOnExit()
            }
        latestTmpFile = tmpFile
        return FileProvider.getUriForFile(requireContext(),
            "com.android.ama_patrol.fileProvider",
            tmpFile)
    }

    private fun getBitmap(contentResolver: ContentResolver, fileUri: Uri): Bitmap? {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, fileUri))
            } else {
                MediaStore.Images.Media.getBitmap(contentResolver, fileUri)
            }
        } catch (e: Exception) {
            null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}