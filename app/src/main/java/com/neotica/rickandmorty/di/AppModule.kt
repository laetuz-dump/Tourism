package com.neotica.rickandmorty.di

import com.neotica.core.di.databaseModule
import com.neotica.core.di.networkModule
import com.neotica.core.di.repositoryModule
import com.neotica.core.domain.usecase.CharacterInteractor
import com.neotica.core.domain.usecase.CharacterUseCase
import com.neotica.rickandmorty.detail.DetailCharacterViewModel
import com.neotica.rickandmorty.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CharacterUseCase> { CharacterInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailCharacterViewModel(get()) }
}

val modules = listOf(
    databaseModule,
    networkModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)