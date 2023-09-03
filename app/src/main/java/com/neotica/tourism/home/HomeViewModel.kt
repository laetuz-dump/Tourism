package com.neotica.tourism.home

import androidx.lifecycle.ViewModel
import com.neotica.core.data.Resource
import com.neotica.core.domain.model.Tourism
import com.neotica.core.domain.usecase.TourismUseCase
import kotlinx.coroutines.flow.Flow

class HomeViewModel(tourismUseCase: TourismUseCase) : ViewModel() {
    val tourism:Flow<Resource<List<Tourism>>> = tourismUseCase.getAllTourism()
}

