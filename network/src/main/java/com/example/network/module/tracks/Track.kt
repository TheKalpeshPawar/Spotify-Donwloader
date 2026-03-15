package com.example.network.module.tracks

import kotlinx.serialization.Serializable

@Serializable
data class Track(
    val albumOfTrack: AlbumOfTrack,
    val artists: Artists,
    val duration: Duration,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)