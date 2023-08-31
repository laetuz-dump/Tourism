package com.neotica.tourism.favorite

import androidx.lifecycle.ViewModel
import com.neotica.tourism.core.domain.usecase.TourismUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(tourismUseCase: TourismUseCase) : ViewModel() {
    val favoriteTourism = tourismUseCase.getFavoriteTourism()
}

