package com.ama_patrol.data.models.map

import com.google.gson.annotations.SerializedName

data class Distance(
    @SerializedName("text")
    var text: String?,
    @SerializedName("value")
    var value: Int?
)
