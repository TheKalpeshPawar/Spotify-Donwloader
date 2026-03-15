package com.example.network.module.albums

import kotlinx.serialization.Serializable

@Serializable
data class Artists(
    val items: List<Artist>
)