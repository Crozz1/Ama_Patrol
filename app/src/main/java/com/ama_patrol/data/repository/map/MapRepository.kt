package com.ama_patrol.data.repository.map

import com.ama_patrol.data.models.map.DirectionResponses
import com.ama_patrol.data.network.Result
import kotlinx.coroutines.flow.Flow

interface MapRepository {
    suspend fun getDirection(
        origin: String, destination: String,
    ): Flow<Result<DirectionResponses?>>
}