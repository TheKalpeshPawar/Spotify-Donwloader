package com.example.network.module.trackDetails

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val artists: List<Artist>,
    val id: String,
    val images: List<Image>,
    val name: String,
    val release_date: String,
    val total_tracks: Int
)