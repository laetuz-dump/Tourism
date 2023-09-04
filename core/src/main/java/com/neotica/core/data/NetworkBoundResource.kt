package com.neotica.core.data

import com.neotica.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {

    // Define a Flow<Resource<ResultType>> as the result
    protected open val result: Flow<Resource<ResultType>> = flow {
        // Emit loading state
        emit(Resource.Loading())

        val dbSource = loadFromDB().first()

        if (shouldFetch(dbSource)) {
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    // Save the data to the database
                    saveCallResult(apiResponse.data)

                    // Emit the loaded data from the database as a success state
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    protected open fun onFetchFailed() {}

    // Define an abstract function for deciding whether to fetch from the network
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    // Define a public function to expose the result as a Flow<Resource<ResultType>>
    fun asFlow(): Flow<Resource<ResultType>> = result
}