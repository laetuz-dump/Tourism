package com.neotica.tourism.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neotica.tourism.core.ui.ViewModelFactory
import com.neotica.tourism.detail.DetailTourismViewModel
import com.neotica.tourism.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailTourismViewModel::class)
    abstract fun bindDetailTourismViewModel(viewModel: DetailTourismViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}