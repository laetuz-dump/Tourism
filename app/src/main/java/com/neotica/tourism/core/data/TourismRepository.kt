package com.neotica.tourism.core.data

import com.neotica.tourism.core.data.source.local.LocalDataSource
import com.neotica.tourism.core.data.source.remote.RemoteDataSource
import com.neotica.tourism.core.domain.repository.ITourismRepository
import com.neotica.tourism.core.data.source.remote.network.ApiResponse
import com.neotica.tourism.core.data.source.remote.response.TourismResponse
import com.neotica.tourism.core.domain.model.Tourism
import com.neotica.tourism.core.utils.AppExecutors
import com.neotica.tourism.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TourismRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITourismRepository {

    override fun getAllTourism(): Flow<Resource<List<Tourism>>> =
        object : NetworkBoundResource<List<Tourism>, List<TourismResponse>>(),
            Flow<Resource<List<Tourism>>> {
            override fun loadFromDB(): Flow<List<Tourism>> {
                return localDataSource.getAllTourism().map { DataMapper.mapEntitiesToDomain(it) }
                }

            override suspend fun createCall(): Flow<ApiResponse<List<TourismResponse>>> =
                remoteDataSource.getAllTourism()


            override suspend fun saveCallResult(data: List<TourismResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                withContext(Dispatchers.IO){
                    localDataSource.insertTourism(tourismList)
                }
            }

            override fun shouldFetch(data: List<Tourism>?): Boolean =
                data.isNullOrEmpty()


            override suspend fun collect(collector: FlowCollector<Resource<List<Tourism>>>) {

            }
        }.asFlow()

    override fun getFavoriteTourism(): Flow<List<Tourism>> {
        return localDataSource.getFavoriteTourism().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
    }
}

