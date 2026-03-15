package com.example.network.module.trackDetails

import kotlinx.serialization.Serializable

@Serializable
data class Track(
    val album: Album,
    val artists: List<Artist>,
    val duration_ms: Int,
    val id: String,
    val name: String
)