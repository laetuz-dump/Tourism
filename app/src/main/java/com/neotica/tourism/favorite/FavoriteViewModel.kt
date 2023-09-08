package com.neotica.tourism.favorite

import androidx.lifecycle.ViewModel
import com.neotica.tourism.core.domain.usecase.TourismUseCase

class FavoriteViewModel(tourismUseCase: TourismUseCase) : ViewModel() {
    val favoriteTourism = tourismUseCase.getFavoriteTourism()
}

