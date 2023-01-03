package com.ama_patrol.data.network.datasource.map

import com.ama_patrol.data.models.map.DirectionResponses
import com.ama_patrol.data.network.ClientRequest
import com.ama_patrol.data.network.ErrorUtils
import com.ama_patrol.data.network.Result
import retrofit2.Response

class MapDataSource {

    private val retrofit = ClientRequest.retrofitInstance!!
    private val mapService: MapService = retrofit.create(MapService::class.java)

    suspend fun getDirection(
        origin: String, destination: String,
    ): Result<DirectionResponses> {
        return getResponse(
            request = {
                mapService.getDirection(
                    origin = origin, destination = destination,
                )
            },
            defaultErrorMessage = "Error get direction")
    }

    private suspend fun <T> getResponse(
        request: suspend () -> Response<T>,
        defaultErrorMessage: String
    ): Result<T> {
        return try {
            println("I'm working in thread ${Thread.currentThread().name}")
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("Unknown Error", null)
        }
    }
}