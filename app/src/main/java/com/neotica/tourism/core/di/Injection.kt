package com.neotica.tourism.core.di

import android.content.Context

import com.neotica.tourism.core.data.source.local.LocalDataSource
import com.neotica.tourism.core.data.source.local.room.TourismDatabase

import com.neotica.tourism.core.data.TourismRepository
import com.neotica.tourism.core.data.source.remote.RemoteDataSource
import com.neotica.tourism.core.domain.repository.ITourismRepository
import com.neotica.tourism.core.domain.usecase.TourismInteractor
import com.neotica.tourism.core.domain.usecase.TourismUseCase
import com.neotica.tourism.core.utils.AppExecutors
import com.neotica.tourism.core.utils.JsonHelper

object Injection {
    private fun provideRepository(context: Context): ITourismRepository {
        val database = TourismDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.tourismDao())
        val appExecutors = AppExecutors()

        return TourismRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideTourismUseCase(context: Context): TourismUseCase {
        val repository = provideRepository(context)
        return TourismInteractor(repository)
    }
}
