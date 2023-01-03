package com.ama_patrol.presentation.menu.ots.aquistion

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ama_patrol.R
import com.ama_patrol.databinding.VisitAquistionBinding
import com.ama_patrol.data.localdatabase.Note
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import com.google.maps.android.PolyUtil
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*


class VisitAcquisitionFragment : Fragment() {

    private lateinit var mMap: GoogleMap
    private var directionLine: Polyline? = null
    private var _binding: VisitAquistionBinding? = null
    private var mapReady = false
    private var mapMaker: HashMap<Int, Marker> = hashMapOf()
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireContext())
    }
    private var cancellationTokenSource = CancellationTokenSource()
    private var note: Note? = null

    private val aquistionViewModel by viewModels<AquistionViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = VisitAquistionBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val mapFragment = childFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            mMap = googleMap

            mapReady = true
            enableMyLocation()
            setOnMapClickListener(googleMap)

        }
        return root

    }
    private fun setOnMapClickListener(googleMap: GoogleMap) {
        googleMap.setOnMapClickListener { location ->
            aquistionViewModel.onAction(
                AquistionAction.EnteredLatitude(
                    startLocation = mapMaker[0]?.position ?: LatLng(0.0, 0.0),
                    endLocation = location
                )
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            mMap.isMyLocationEnabled = true
            if (isLocationEnabled(requireContext())) {
                requestCurrentLocation()
            } else {
                showGPSNotEnabledDialog(requireContext())
            }
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(requireContext(), "Permission Needeed", Toast.LENGTH_SHORT).show()
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            } else {
                Toast.makeText(requireContext(), "Permission Needeed", Toast.LENGTH_SHORT).show()
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
                enableMyLocation()
            } else {
                Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

    private val requestSettingLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                enableMyLocation()
            }
        }

    private fun showGPSNotEnabledDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.enable_gps))
            .setMessage(context.getString(R.string.required_for_this_app))
            .setCancelable(false)
            .setPositiveButton(context.getString(R.string.enable_now)) { _, _ ->
                requestSettingLauncher.launch(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
            .show()
    }

    @SuppressLint("MissingPermission")
    private fun requestCurrentLocation() {
        Log.d(TAG, "requestCurrentLocation()")
        val currentLocationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
            LocationRequest.QUALITY_BALANCED_POWER_ACCURACY, cancellationTokenSource.token
        )

        currentLocationTask.addOnCompleteListener { task: Task<Location> ->
            if (task.isSuccessful && task.result != null) {
                val result: Location = task.result
                "Location (success): ${result.latitude}, ${result.longitude}"
                Log.d(TAG, "getCurrentLocation() result: $result")

                val zoomLevel = 15f
                val marker = LatLng(result.latitude, result.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, zoomLevel))
                mMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(result.latitude, result.longitude))
                        .title("Current Location")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                )?.let { currentLocationMarker ->
                    mapMaker[0] = currentLocationMarker
                }
                mMap.maxZoomLevel


                if (note != null) {
                   aquistionViewModel.onAction(
                       AquistionAction.EnteredLatitude(
                           startLocation = mapMaker[0]?.position ?: LatLng(0.0, 0.0),
                           endLocation = LatLng(note?.latitude ?: 0.0, note?.longitude ?: 0.0)
                       )
                    )
                }
            } else {
                val exception = task.exception
                Log.d("Location (failure): ", exception?.message.orEmpty())
            }

        }
    }
    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager: LocationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            aquistionViewModel.eventFlow.collectLatest { event ->
                when (event) {
                    is AquistionEffect.DrawPolygon -> {
                        drawPolyLine(event.location, event.shape)
                    }
                    AquistionEffect.SaveNote -> {}
                    is AquistionEffect.ShowErrorMessage -> {}
                }
            }
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_ots_fragment_to_visit_acquisition_fragment)
        }
//        binding.btnFormOts.setOnClickListener {
//            findNavController().navigate(R.id.action_visitMaintenanceFragment_to_formMaintenace1)
//        }
    }

    //    private fun resetFields() = with(binding) {
//        mapMaker[1]?.remove()
//        directionLine?.remove()
//    }
    private fun drawPolyLine(location: LatLng, shape: String) {
        directionLine?.remove()

        val decode = PolyUtil.decode(shape)
        val polyLine = PolylineOptions()
            .addAll(decode)
            .width(8f)
            .color(Color.BLUE)
            .geodesic(false)
        directionLine = mMap.addPolyline(polyLine)

        mMap.setOnPolylineClickListener { polyline ->
            polyline.color = polyline.color xor 0x000000ff
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(decode.last(), 15f))
        val snippet = String.format(
            Locale.getDefault(),
            "Lat: %1$.5f, Long: %2$.5f",
            location.latitude,
            location.longitude
        )
        if (mapMaker[1] == null) {
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(location.latitude, location.longitude))
                    .title("end destination")
                    .snippet(snippet)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )?.let { appointmentMarker ->
                appointmentMarker.showInfoWindow()
                mapMaker[1] = appointmentMarker
            }
        } else {
            val appointmentMarker = mapMaker[1]
            appointmentMarker?.remove()
            mapMaker.remove(1)

            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(location.latitude, location.longitude))
                    .title("end destination")
                    .snippet(snippet)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )?.let { newAppointmentMarker ->
                newAppointmentMarker.showInfoWindow()
                mapMaker[1] = newAppointmentMarker
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "MaintenanceFragment"
    }
}