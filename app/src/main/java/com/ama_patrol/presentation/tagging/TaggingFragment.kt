package com.ama_patrol.presentation.tagging


import android.app.AlertDialog
import android.app.SearchManager
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ama_patrol.databinding.TaggingLayoutBinding
import com.google.zxing.integration.android.IntentIntegrator


class TaggingFragment : Fragment() {

    private var _binding: TaggingLayoutBinding? = null
    private val binding get() = _binding!!
//    private fun allPermissionsGranted() = permissions.all {
//        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
//    }
//    private val permissions = mutableListOf(
//        android.Manifest.permission.CAMERA
//    )
//    private var scan_btn: Button? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TaggingLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnScan.setOnClickListener {
            val integrator = IntentIntegrator.forSupportFragment(this@TaggingFragment)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            integrator.setPrompt("SCAN")
            integrator.setCameraId(0)
            integrator.setBeepEnabled(true)
            integrator.setBarcodeImageEnabled(true)
            integrator.initiateScan()
        }
    }

    
     @Deprecated("Deprecated in Java")
     override fun  onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
         val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
         if (result != null) {
             AlertDialog.Builder(requireContext())
                 .setMessage("Apakah Anda Ingin Membuka Lokasi ini ${result.contents}")
                 .setPositiveButton("Ya",
                 { dialogInterface, i ->
                     val intent =
                      Intent (Intent.ACTION_WEB_SEARCH)
                         intent.putExtra(SearchManager.QUERY,result.contents)
                        startActivity(intent)
                 })
                 .setNegativeButton("Tidak", {
                         dialogInterface, i -> })
                     .create()
                     .show()

         }
     }





}