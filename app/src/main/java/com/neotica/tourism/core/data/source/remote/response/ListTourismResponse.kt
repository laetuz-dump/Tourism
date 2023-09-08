package com.neotica.tourism.core.data.source.remote.response

data class ListTourismResponse (
    val error: Boolean,
    val message: String,
    val places: List<TourismResponse>
)