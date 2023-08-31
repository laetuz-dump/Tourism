package com.neotica.tourism.home

import androidx.lifecycle.ViewModel
import com.neotica.tourism.core.data.Resource
import com.neotica.tourism.core.domain.model.Tourism
import com.neotica.tourism.core.domain.usecase.TourismUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeViewModel @Inject constructor(tourismUseCase: TourismUseCase) : ViewModel() {
    val tourism:Flow<Resource<List<Tourism>>> = tourismUseCase.getAllTourism()
}

