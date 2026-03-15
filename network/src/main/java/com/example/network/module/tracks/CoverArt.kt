package com.example.network.module.tracks

import kotlinx.serialization.Serializable

@Serializable
data class CoverArt(
    val height: Int,
    val url: String,
    val width: Int
)