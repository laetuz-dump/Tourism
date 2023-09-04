package com.neotica.maps

import androidx.lifecycle.ViewModel
import com.neotica.core.domain.usecase.TourismUseCase

class MapsViewModel(useCase: TourismUseCase): ViewModel() {
    val tourism = useCase.getAllTourism()
}