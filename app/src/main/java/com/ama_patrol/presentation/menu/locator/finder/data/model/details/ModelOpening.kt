package com.ama_patrol.presentation.menu.locator.finder.data.model.details

import com.google.gson.annotations.SerializedName

class ModelOpening {
    @SerializedName("open_now")
    var openNow: Boolean? = null

    @SerializedName("weekday_text")
    lateinit var weekdayText: List<String>
}