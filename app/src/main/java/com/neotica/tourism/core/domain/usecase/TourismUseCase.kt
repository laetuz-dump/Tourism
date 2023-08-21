package com.neotica.tourism.core.domain.usecase

import androidx.lifecycle.LiveData
import com.neotica.tourism.core.data.Resource
import com.neotica.tourism.core.domain.model.Tourism

interface TourismUseCase {
    fun getAllTourism(): LiveData<Resource<List<Tourism>>>
    fun getFavoriteTourism(): LiveData<List<Tourism>>
    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}