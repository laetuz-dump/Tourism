package com.neotica.core.data.source.local

import com.neotica.core.data.source.local.entity.CharacterEntity
import com.neotica.core.data.source.local.room.CharacterDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LocalDataSource (private val charDao: CharacterDao) {

    fun getAllTourism(): Flow<List<CharacterEntity>> = charDao.getAllCharacter()

    fun getFavoriteTourism(): Flow<List<CharacterEntity>> = charDao.getFavoriteCharacter()

    fun insertTourism(charList: List<CharacterEntity>) = charDao.insertCharacter(charList)

    fun setFavoriteTourism(char: CharacterEntity, newState: Boolean) {
        char.isFavorite = newState
        CoroutineScope(Dispatchers.IO).launch {
            charDao.updateFavoriteCharacter(char)
        }

    }
}