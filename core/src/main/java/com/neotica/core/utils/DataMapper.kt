package com.neotica.core.utils

import com.neotica.core.data.source.local.entity.CharacterEntity
import com.neotica.core.data.source.remote.response.CharacterResponse
import com.neotica.core.domain.model.Character

object DataMapper {
    fun mapResponsesToEntities(input: List<CharacterResponse>): List<CharacterEntity> {
        val charList = ArrayList<CharacterEntity>()
        input.map {
            val character = CharacterEntity(
                charId = it.id,
                name = it.name,
                status = it.status,
                species = it.species,
                type = it.type,
                gender = it.gender,
                image = it.image,
                isFavorite = false
            )
            charList.add(character)
        }
        return charList
    }

    fun mapEntitiesToDomain(input: List<CharacterEntity>): List<Character> =
        input.map {
            Character(
                id = it.charId,
                name = it.name,
                status = it.status,
                species = it.species,
                type = it.type,
                gender = it.gender,
                image = it.image,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(it: Character) = CharacterEntity(
        charId = it.id,
        name = it.name,
        status = it.status,
        species = it.species,
        type = it.type,
        gender = it.gender,
        image = it.image,
        isFavorite = false
    )
}