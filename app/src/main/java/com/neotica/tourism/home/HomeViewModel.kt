package com.neotica.tourism.home

import androidx.lifecycle.ViewModel
import com.neotica.core.data.Resource
import com.neotica.core.domain.model.Character
import com.neotica.core.domain.usecase.CharacterUseCase
import kotlinx.coroutines.flow.Flow

class HomeViewModel(characterUseCase: CharacterUseCase) : ViewModel() {
    val character:Flow<Resource<List<Character>>> = characterUseCase.getAllCharacter()
}

