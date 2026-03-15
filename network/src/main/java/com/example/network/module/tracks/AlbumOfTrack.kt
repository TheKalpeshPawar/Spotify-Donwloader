package com.example.network.module.tracks

import kotlinx.serialization.Serializable

@Serializable
data class AlbumOfTrack(
    val coverArt: List<CoverArt>,
    val id: String,
    val name: String
)