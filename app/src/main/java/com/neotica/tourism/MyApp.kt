package com.neotica.tourism

import android.app.Application
import com.neotica.tourism.core.di.CoreComponent
import com.neotica.tourism.core.di.DaggerCoreComponent
import com.neotica.tourism.di.AppComponent
import com.neotica.tourism.di.DaggerAppComponent

open class MyApp: Application() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}