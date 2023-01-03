package com.ama_patrol.presentation.menu.locator

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.*
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ama_patrol.R
import com.ama_patrol.data.localdatabase.Note
import com.ama_patrol.databinding.LocatorBinding
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
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.util.*


class LocatorFragment : Fragment() {
//
//    private lateinit var mMap: GoogleMap
//    private var directionLine: Polyline? = null
//    private var _binding: LocatorBinding? = null
//    private var mapReady = false
//    private var mapMaker: HashMap<Int, Marker> = hashMapOf()
//
//
//    lateinit var editText: EditText
//    lateinit var button: Button
//    private var btnFindPath: Button? = null
//    private val etOrigin: EditText? = null
//    private val etDestination: EditText? = null
//    private val originMarkers: List<Marker> = ArrayList()
//    private val destinationMarkers: List<Marker> = ArrayList()
//    private val polylinePaths: List<Polyline> = ArrayList()
//    private val progressDialog: ProgressDialog? = null
//
////    internal lateinit var mLastLocation: Location
////
////    internal var mGoogleApiClient: GoogleApiClient? = null
////    internal lateinit var mLocationRequest: LocationRequest
//
//    private val fusedLocationClient: FusedLocationProviderClient by lazy {
//        LocationServices.getFusedLocationProviderClient(requireContext())
//    }
//
//    private var cancellationTokenSource = CancellationTokenSource()
//    private var note: Note? = null
//
//    private val locatorViewModel by viewModels<LocatorViewModel>()
//
//    private val binding get() = _binding!!
//
//    @SuppressLint("PotentialBehaviorOverride")
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        _binding = LocatorBinding.inflate(inflater, container, false)
//        val view = binding.root
//
//        val mapFragment = childFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment
//        mapFragment.getMapAsync { googleMap ->
//            mMap = googleMap
//
//            mapReady = true
//            enableMyLocation()
//
//            setOnMapClickListener(googleMap)
//        }
//        editText = view.findViewById(R.id.etdestination)
//        editText = view.findViewById(R.id.etorigin)
//        // button = findViewById(R.id.btnFindPath)
//        binding.maps.setOnClickListener {
//            sendRequest()
//        }
//        return view
//
//    }
//
//    private fun sendRequest() {
//        TODO("Not yet implemented")
//    }
//
////         private fun sendRequest() {
////        val origin: String = etOrigin.toString()
////        val destination: String = etDestination.toString()
////        if (origin.isEmpty()) {
////            Toast.makeText(requireContext(), "Please enter origin address!", Toast.LENGTH_SHORT).show();
////            return;
////        }
////        if (destination.isEmpty()) {
////            Toast.makeText(requireContext(), "Please enter destination address!", Toast.LENGTH_SHORT).show();
////            return;
////        }
////
////             try {
////                 DirectionFinder(this, origin, destination).execute()
////             } catch (e: UnsupportedEncodingException) {
////                 e.printStackTrace()
////             }
////    }
////    }
//
//
//    private fun setOnMapClickListener(googleMap: GoogleMap) {
//        googleMap.setOnMapClickListener { location ->
//            locatorViewModel.onAction(
//                LocatorAction.EnteredLatitude(
//                    startLocation = mapMaker[0]?.position ?: LatLng(0.0, 0.0),
//                    endLocation = location
//                )
//            )
//        }
//    }
//
//    @SuppressLint("MissingPermission")
//    private fun enableMyLocation() {
//        if (isPermissionGranted()) {
//            mMap.isMyLocationEnabled = true
//            if (isLocationEnabled(requireContext())) {
//                requestCurrentLocation()
//            } else {
//                showGPSNotEnabledDialog(requireContext())
//            }
//        } else {
//            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
//                Toast.makeText(requireContext(), "Permission Needeed", Toast.LENGTH_SHORT).show()
//                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//            } else {
//                Toast.makeText(requireContext(), "Permission Needeed", Toast.LENGTH_SHORT).show()
//                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//            }
//        }
//    }
//
//    private val requestPermissionLauncher =
//        registerForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ) { isGranted: Boolean ->
//            if (isGranted) {
//                Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
//                enableMyLocation()
//            } else {
//                Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    private val requestSettingLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == RESULT_OK) {
//                enableMyLocation()
//            }
//        }
//
//    private fun showGPSNotEnabledDialog(context: Context) {
//        AlertDialog.Builder(context)
//            .setTitle(context.getString(R.string.enable_gps))
//            .setMessage(context.getString(R.string.required_for_this_app))
//            .setCancelable(false)
//            .setPositiveButton(context.getString(R.string.enable_now)) { _, _ ->
//                requestSettingLauncher.launch(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
//            }
//            .show()
//    }
//
//    @SuppressLint("MissingPermission")
//    private fun requestCurrentLocation() {
//        Log.d(TAG, "requestCurrentLocation()")
//        val currentLocationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
//            LocationRequest.QUALITY_BALANCED_POWER_ACCURACY, cancellationTokenSource.token
//        )
//
//        currentLocationTask.addOnCompleteListener { task: Task<Location> ->
//            if (task.isSuccessful && task.result != null) {
//                       val result: Location = task.result
//                "Location (success): ${result.latitude}, ${result.longitude}"
//                Log.d(TAG, "getCurrentLocation() result: $result")
//
//                val zoomLevel = 15f
//                val marker = LatLng(result.latitude, result.longitude)
//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, zoomLevel))
//                mMap.addMarker(
//                    MarkerOptions()
//                        .position(LatLng(result.latitude, result.longitude))
//                        .title("Current Location")
//                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
//                )?.let { currentLocationMarker ->
//                    mapMaker[0] = currentLocationMarker
//                }
//                mMap.maxZoomLevel
//
//
//                if (note != null) {
//                    locatorViewModel.onAction(
//                        LocatorAction.EnteredLatitude(
//                            startLocation = mapMaker[0]?.position ?: LatLng(0.0, 0.0),
//                            endLocation = LatLng(note?.latitude ?: 0.0, note?.longitude ?: 0.0)
//                        )
//                    )
//                }
//            } else {
//                val exception = task.exception
//                Log.d("Location (failure): ", exception?.message.orEmpty())
//            }
//
//        }
//    }
//
//
//    private fun isPermissionGranted(): Boolean {
//        return ContextCompat.checkSelfPermission(
//            requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED
//    }
//
//    private fun isLocationEnabled(context: Context): Boolean {
//        val locationManager: LocationManager =
//            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
//    }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        lifecycleScope.launch {
//            locatorViewModel.eventFlow.collectLatest { event ->
//                when (event) {
//                    is LocatorEffect.DrawPolygon -> {
//                        drawPolyLine(event.location, event.shape)
//                    }
//                    LocatorEffect.SaveNote -> {}
//                    is LocatorEffect.ShowErrorMessage -> {}
//                }
//            }
//        }
//        binding.btnBack.setOnClickListener {
//            findNavController().navigate(R.id.action_locatorFragment_to_navigation_menu)
//
//
//        }
//    }
////    private fun resetFields() = with(binding) {
////        mapMaker[1]?.remove()
////        directionLine?.remove()
////    }
//    private fun drawPolyLine(location: LatLng, shape: String) {
//        directionLine?.remove()
//
//        val decode = PolyUtil.decode(shape)
//        val polyLine = PolylineOptions()
//            .addAll(decode)
//            .width(8f)
//            .color(Color.BLUE)
//            .geodesic(false)
//        directionLine = mMap.addPolyline(polyLine)
//
//        mMap.setOnPolylineClickListener { polyline ->
//            polyline.color = polyline.color xor 0x000000ff
//        }
//
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(decode.last(), 15f))
//        val snippet = String.format(
//            Locale.getDefault(),
//            "Lat: %1$.5f, Long: %2$.5f",
//            location.latitude,
//            location.longitude
//        )
//        if (mapMaker[1] == null) {
//            mMap.addMarker(
//                MarkerOptions()
//                    .position(LatLng(location.latitude, location.longitude))
//                    .title("end destination")
//                    .snippet(snippet)
//                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
//            )?.let { appointmentMarker ->
//                appointmentMarker.showInfoWindow()
//                mapMaker[1] = appointmentMarker
//            }
//        } else {
//            val appointmentMarker = mapMaker[1]
//            appointmentMarker?.remove()
//            mapMaker.remove(1)
//
//            mMap.addMarker(
//                MarkerOptions()
//                    .position(LatLng(location.latitude, location.longitude))
//                    .title("end destination")
//                    .snippet(snippet)
//                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
//            )?.let { newAppointmentMarker ->
//                newAppointmentMarker.showInfoWindow()
//                mapMaker[1] = newAppointmentMarker
//            }
//        }
//    }
//
//
//
//    @Suppress("DEPRECATION")
//    fun searchLocation(view: View){
//        val locationSearch: EditText = view.findViewById(R.id.etdestination)
//        var location: String
//        location = locationSearch.text.toString().trim()
//        var addressList: List<Address>? = null
//
//        if (location == null || location == ""){
//            Toast.makeText(requireContext(), "provide location", Toast.LENGTH_SHORT).show()
//        }else{
//            val geoCoder = Geocoder(requireContext())
//            try {
//                addressList = geoCoder.getFromLocationName(location, 1)
//            }catch (e: IOException){
//                e.printStackTrace()
//            }
//
//            val address = addressList!![0]
//            val latLng = LatLng(address.latitude, address.longitude)
//            mMap!!.addMarker(MarkerOptions().position(latLng).title(location))
//            mMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))
//        }
//
//    }
//
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    companion object {
//        private const val TAG = "LocatorFragment"
//    }
}




