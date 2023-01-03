package com.ama_patrol.presentation.menu.locator

import android.Manifest
import android.R
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.ama_patrol.databinding.LocatorBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.textfield.TextInputEditText
import okhttp3.Route
import java.io.UnsupportedEncodingException


class LocateFragment : Fragment() {
//
//    private var _binding: LocatorBinding? = null
//    private var mMap: GoogleMap? = null
//    //private var btnFindPath: Button? = null
//    lateinit var editText: EditText
//    lateinit var button: Button
////    private var etOrigin: EditText? = null
////    private var etDestination: EditText? = null
//    private var mapReady = false
//    private var originMarkers: MutableList<Marker?>? = ArrayList()
//    private var destinationMarkers: MutableList<Marker?>? = ArrayList()
//    private var polylinePaths: MutableList<Polyline>? = ArrayList()
//    private var progressDialog: ProgressDialog? = null
//
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
//            editText = findViewById()
////            btnFindPath = view.findViewById(R.id.btnFindPath) as Button
////            etOrigin = view.findViewById(R.id.etOrigin) as EditText
////            etDestination = view.findViewById(R.id.etDestination) as EditText
////            btnFindPath.setOnClickListener
////            (object : OnClickListener() {
////                fun onClick(View?) {
////                    sendRequest()
////                }
////            })
//        }
//
//        private fun sendRequest() {
//            val origin = etOrigin!!.text.toString()
//            val destination = etDestination!!.text.toString()
//            if (origin.isEmpty()) {
//                Toast.makeText(requireContext(), "Please enter origin address!", Toast.LENGTH_SHORT).show()
//                return
//            }
//            if (destination.isEmpty()) {
//                Toast.makeText(requireContext(), "Please enter destination address!", Toast.LENGTH_SHORT).show()
//                return
//            }
//            try {
//                DirectionFinder(this, origin, destination).execute()
//            } catch (e: UnsupportedEncodingException) {
//                e.printStackTrace()
//            }
//        }
//
//        override fun onMapReady(googleMap: GoogleMap) {
//            mMap = googleMap
//            val hcmus = LatLng(10.762963, 106.682394)
//            mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 18f))
//            originMarkers!!.add(
//                mMap!!.addMarker(
//                    MarkerOptions()
//                        .title("Đại học Khoa học tự nhiên")
//                        .position(hcmus)
//                )
//            )
//            if (ActivityCompat.checkSelfPermission(
//                    requireContext(),
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                    requireContext(),
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return
//            }
//            mMap!!.isMyLocationEnabled = true
//        }
//
//        fun onDirectionFinderStart() {
//            progressDialog = ProgressDialog.show(
//                requireContext(), "Please wait.",
//                "Finding direction..!", true
//            )
//            if (originMarkers != null) {
//                for (marker in originMarkers!!) {
//                    marker!!.remove()
//                }
//            }
//            if (destinationMarkers != null) {
//                for (marker in destinationMarkers!!) {
//                    marker!!.remove()
//                }
//            }
//            if (polylinePaths != null) {
//                for (polyline in polylinePaths) {
//                    polyline.remove()
//                }
//            }
//        }
//
//        fun onDirectionFinderSuccess(routes: List<Route?>) {
//            progressDialog!!.dismiss()
//            polylinePaths = ArrayList()
//            originMarkers = ArrayList()
//            destinationMarkers = ArrayList()
//            for (route in routes) {
//                mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16f))
//                (findViewById<View>(R.id.tvDuration) as TextView).setText(route.duration.text)
//                (findViewById<View>(R.id.tvDistance) as TextView).setText(route.distance.text)
//                originMarkers.add(
//                    mMap!!.addMarker(
//                        MarkerOptions()
//                            .icon(BitmapDescriptorFactory.fromResource(R.color.holo_blue_bright))
//                            .title(route.startAddress)
//                            .position(route.startLocation)
//                    )
//                )
//                destinationMarkers.add(
//                    mMap!!.addMarker(
//                        MarkerOptions()
//                            .icon(BitmapDescriptorFactory.fromResource(R.color.holo_green_light))
//                            .title(route.endAddress)
//                            .position(route.endLocation)
//                    )
//                )
//                val polylineOptions = PolylineOptions().geodesic(true).color(Color.BLUE).width(10f)
//                for (i in 0 until route.points.size()) polylineOptions.add(route.points.get(i))
//                polylinePaths!!.add(mMap!!.addPolyline(polylineOptions))
//            }
//        }
//    }
}





