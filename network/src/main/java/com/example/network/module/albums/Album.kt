package com.example.network.module.albums

import com.example.network.module.AlbumDeserializer
import kotlinx.serialization.Serializable

@Serializable(with = AlbumDeserializer::class)
data class Album(
    val artists: Artists,
    val coverArt: List<CoverArt>?,
    val date: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)