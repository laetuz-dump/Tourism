package com.neotica.tourism.core.data.source.remote.network

import com.neotica.tourism.core.data.TourismRepository
import com.neotica.tourism.core.di.DatabaseModule
import com.neotica.tourism.core.di.NetworkModule
import com.neotica.tourism.core.domain.repository.ITourismRepository
import dagger.Binds
import dagger.Module

@Module(includes = [DatabaseModule::class, NetworkModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(tourismRepository: TourismRepository): ITourismRepository
}