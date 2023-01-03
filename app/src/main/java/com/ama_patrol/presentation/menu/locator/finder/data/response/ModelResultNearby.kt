package com.ama_patrol.presentation.menu.locator.finder.data.response

import com.ama_patrol.presentation.menu.locator.finder.data.model.nearby.ModelResults
import com.google.gson.annotations.SerializedName


class ModelResultNearby {
    @SerializedName("results")
    lateinit var modelResults: List<ModelResults>
}