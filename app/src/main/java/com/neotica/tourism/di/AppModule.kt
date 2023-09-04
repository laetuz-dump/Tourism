package com.neotica.tourism.di

import com.neotica.core.di.databaseModule
import com.neotica.core.di.networkModule
import com.neotica.core.di.repositoryModule
import com.neotica.core.domain.usecase.TourismInteractor
import com.neotica.core.domain.usecase.TourismUseCase
import com.neotica.tourism.detail.DetailTourismViewModel
import com.neotica.tourism.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TourismUseCase> { TourismInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailTourismViewModel(get()) }
}

val modules = listOf(
    databaseModule,
    networkModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)