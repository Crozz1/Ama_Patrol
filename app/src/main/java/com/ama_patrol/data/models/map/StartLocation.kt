package com.ama_patrol.data.models.map

import com.google.gson.annotations.SerializedName

data class StartLocation(
    @SerializedName("lat")
    var lat: Double?,
    @SerializedName("lng")
    var lng: Double?
)
