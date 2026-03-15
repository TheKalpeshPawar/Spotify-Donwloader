package com.example.network.module.tracks

import kotlinx.serialization.Serializable

@Serializable
data class Artists(
    val items: List<Artist>
)