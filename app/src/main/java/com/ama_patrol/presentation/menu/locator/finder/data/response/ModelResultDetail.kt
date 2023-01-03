package com.ama_patrol.presentation.menu.locator.finder.data.response

import com.ama_patrol.presentation.menu.locator.finder.data.model.details.ModelDetail
import com.google.gson.annotations.SerializedName

class ModelResultDetail {
    @SerializedName("result")
    lateinit var modelDetail: ModelDetail
}