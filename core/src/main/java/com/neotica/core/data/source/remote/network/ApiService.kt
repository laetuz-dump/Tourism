package com.neotica.core.data.source.remote.network

import com.neotica.core.data.source.remote.response.CharacterListResponse
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    suspend fun getList(): CharacterListResponse
}