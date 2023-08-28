package com.neotica.tourism.di

import com.neotica.tourism.core.di.CoreComponent
import com.neotica.tourism.detail.DetailTourismActivity
import com.neotica.tourism.favorite.FavoriteFragment
import com.neotica.tourism.home.HomeFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }
    fun inject(fragment: HomeFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: DetailTourismActivity)
}