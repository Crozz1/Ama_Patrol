package com.ama_patrol.presentation.menu.locator.finder.data.model.nearby

import com.ama_patrol.presentation.menu.locator.finder.data.model.nearby.ModelLocation
import com.google.gson.annotations.SerializedName

class ModelGeometry {
    @SerializedName("location")
    lateinit var modelLocation: ModelLocation
}