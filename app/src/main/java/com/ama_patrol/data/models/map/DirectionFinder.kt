package com.ama_patrol.data.models.map

import android.os.AsyncTask
import com.google.android.gms.maps.model.LatLng
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder


class DirectionFinder {

//        private var listener: DirectionFinderListener?= null
//        private val origin: String?= null
//        private val destination: String?= null
//
//
//
//        @Throws(UnsupportedEncodingException::class)
//        private fun execute() {
//            listener.onDirectionFinderStart()
//            DownloadRawData().execute(createUrl())
//        }
//
//        @Throws(UnsupportedEncodingException::class)
//        private fun createUrl(): String {
//            val urlOrigin: String = URLEncoder.encode(origin, "utf-8")
//            val urlDestination: String = URLEncoder.encode(destination, "utf-8")
//            return DIRECTION_URL_API + "origin=" + urlOrigin + "&destination=" + urlDestination + "&key=" + GOOGLE_API_KEY
//        }
//
//        private inner class DownloadRawData :
//            AsyncTask<String?, Void?, String?>() {
//            protected override fun doInBackground(vararg params: String): String? {
//                val link = params[0]
//                try {
//                    val url = URL(link)
//                    val `is`: InputStream = url.openConnection().getInputStream()
//                    val buffer = StringBuffer()
//                    val reader = BufferedReader(InputStreamReader(`is`))
//                    var line: String
//                    while (reader.readLine().also { line = it } != null) {
//                        buffer.append(
//                            """
//                            $line
//
//                            """.trimIndent()
//                        )
//                    }
//                    return buffer.toString()
//                } catch (e: MalformedURLException) {
//                    e.printStackTrace()
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//                return null
//            }
//
//            override fun onPostExecute(res: String?) {
//                try {
//                    parseJSon(res)
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            }
//        }
//
//        @Throws(JSONException::class)
//        private fun parseJSon(data: String?) {
//            if (data == null) return
//            val routes: MutableList<Route?> = ArrayList()
//            val jsonData = JSONObject(data)
//            val jsonRoutes = jsonData.getJSONArray("routes")
//            for (i in 0 until jsonRoutes.length()) {
//                val jsonRoute = jsonRoutes.getJSONObject(i)
//                val route = Route()
//                val overview_polylineJson = jsonRoute.getJSONObject("overview_polyline")
//                val jsonLegs = jsonRoute.getJSONArray("legs")
//                val jsonLeg = jsonLegs.getJSONObject(0)
//                val jsonDistance = jsonLeg.getJSONObject("distance")
//                val jsonDuration = jsonLeg.getJSONObject("duration")
//                val jsonEndLocation = jsonLeg.getJSONObject("end_location")
//                val jsonStartLocation = jsonLeg.getJSONObject("start_location")
//                //day.text = getString(R.string.set)
//                route.distance =
//                    Distance.(jsonDistance.getString("text"), jsonDistance.getInt("value"))
//                route.duration =
//                    Duration(jsonDuration.getString("text"), jsonDuration.getInt("value"))
//                route.endaddress = jsonLeg.getString("end_address")
//                route.startaddress = jsonLeg.getString("start_address")
//                route.startlocation =
//                    LatLng(jsonStartLocation.getDouble("lat"), jsonStartLocation.getDouble("lng"))
//                route.endLocation =
//                    LatLng(jsonEndLocation.getDouble("lat"), jsonEndLocation.getDouble("lng"))
//                route.points = decodePolyLine(overview_polylineJson.getString("points"))
//                routes.add(route)
//            }
//            listener.onDirectionFinderSuccess(routes)
//        }
//
//        private fun decodePolyLine(poly: String): List<LatLng> {
//            val len = poly.length
//            var index = 0
//            val decoded: MutableList<LatLng> = ArrayList()
//            var lat = 0
//            var lng = 0
//            while (index < len) {
//                var b: Int
//                var shift = 0
//                var result = 0
//                do {
//                    b = poly[index++].code - 63
//                    result = result or (b and 0x1f shl shift)
//                    shift += 5
//                } while (b >= 0x20)
//                val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
//                lat += dlat
//                shift = 0
//                result = 0
//                do {
//                    b = poly[index++].code - 63
//                    result = result or (b and 0x1f shl shift)
//                    shift += 5
//                } while (b >= 0x20)
//                val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
//                lng += dlng
//                decoded.add(
//                    LatLng(
//                        lat / 100000.0, lng / 100000.0
//                    )
//                )
//            }
//            return decoded
//        }
//
//        companion object {
//            private const val DIRECTION_URL_API =
//                "https://maps.googleapis.com/maps/api/directions/json?"
//            private const val GOOGLE_API_KEY = "AIzaSyACCfmrZXMU1ZA7U3f9CP7tEhao3yS8ow0"
//        }

}