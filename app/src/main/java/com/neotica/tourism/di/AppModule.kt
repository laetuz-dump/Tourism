package com.neotica.tourism.di

import com.neotica.tourism.core.domain.usecase.TourismInteractor
import com.neotica.tourism.core.domain.usecase.TourismUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideTourismUseCase(tourismInteractor: TourismInteractor): TourismUseCase
}