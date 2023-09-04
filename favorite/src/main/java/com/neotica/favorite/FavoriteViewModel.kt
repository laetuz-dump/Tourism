package com.neotica.favorite

import androidx.lifecycle.ViewModel
import com.neotica.core.domain.usecase.CharacterUseCase

class FavoriteViewModel(charUseCase: CharacterUseCase) : ViewModel() {
    val favoriteCharacter = charUseCase.getFavoriteCharacter()
}

