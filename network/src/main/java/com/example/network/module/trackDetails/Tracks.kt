package com.example.network.module.trackDetails

import kotlinx.serialization.Serializable

@Serializable
data class Tracks(
    val tracks: List<Track>
)