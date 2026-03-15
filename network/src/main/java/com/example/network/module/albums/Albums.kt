package com.example.network.module.albums

import kotlinx.serialization.Serializable

@Serializable
data class Albums(
    val items: List<Album>,
    val totalCount: Int
)