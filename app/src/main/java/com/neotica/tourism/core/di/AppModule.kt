package com.neotica.tourism.core.di

import com.neotica.tourism.core.domain.usecase.TourismInteractor
import com.neotica.tourism.core.domain.usecase.TourismUseCase
import com.neotica.tourism.detail.DetailTourismViewModel
import com.neotica.tourism.favorite.FavoriteViewModel
import com.neotica.tourism.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TourismUseCase> { TourismInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailTourismViewModel(get()) }
}

val modules = listOf(
    databaseModule,
    networkModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)