package com.neotica.core.data.source.remote.response

data class CharacterListResponse (
    val info: Info,
    val results: List<CharacterResponse>
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)