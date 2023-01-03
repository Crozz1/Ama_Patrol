package com.ama_patrol.data.network

data class Error(
    val status_code: Int = 0,
    val status_message: String? = null
)