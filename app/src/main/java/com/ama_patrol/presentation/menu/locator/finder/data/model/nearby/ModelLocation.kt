package com.ama_patrol.presentation.menu.locator.finder.data.model.nearby

import com.google.gson.annotations.SerializedName


class ModelLocation {
    @SerializedName("lat")
    var lat: Double = 0.0

    @SerializedName("long")
    var lng: Double = 0.0
}