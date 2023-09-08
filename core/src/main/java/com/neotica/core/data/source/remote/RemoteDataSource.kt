package com.neotica.core.data.source.remote

import android.util.Log
import com.neotica.core.data.source.remote.network.ApiResponse
import com.neotica.core.data.source.remote.network.ApiService
import com.neotica.core.data.source.remote.response.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val apiService: ApiService) {

    suspend fun getAllCharacter(): Flow<ApiResponse<List<CharacterResponse>>> {

        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("remoteSource:", e.toString() )
            }
        }.flowOn(Dispatchers.IO)
    }
}

