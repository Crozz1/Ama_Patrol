package com.ama_patrol.data.repository.map

import com.ama_patrol.data.models.map.DirectionResponses
import com.ama_patrol.data.network.datasource.map.MapDataSource
import com.ama_patrol.data.network.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MapRepositoryImpl(private val mapDataSource: MapDataSource) : MapRepository {

    override suspend fun getDirection(
        origin: String, destination: String,
    ): Flow<Result<DirectionResponses?>> {
        return flow {
            emit(Result.loading())
            val result = mapDataSource.getDirection(
                origin = origin, destination = destination,
            )
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}