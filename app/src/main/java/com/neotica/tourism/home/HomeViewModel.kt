package com.neotica.tourism.home

import androidx.lifecycle.ViewModel
import com.neotica.tourism.core.domain.usecase.TourismUseCase

class HomeViewModel(tourismUseCase: TourismUseCase) : ViewModel() {
    val tourism = tourismUseCase.getAllTourism()
}

