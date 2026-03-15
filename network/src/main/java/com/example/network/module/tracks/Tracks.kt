package com.example.network.module.tracks

import kotlinx.serialization.Serializable

@Serializable
data class Tracks(
    val items: List<Track>,
    val totalCount: Int
)