package com.ama_patrol.data.network.datasource.map

import com.ama_patrol.data.models.map.DirectionResponses
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MapService {

    @GET("maps/api/directions/json")
    suspend fun getDirection(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") apiKey: String = "AIzaSyACCfmrZXMU1ZA7U3f9CP7tEhao3yS8ow0"
    ): Response<DirectionResponses>
}