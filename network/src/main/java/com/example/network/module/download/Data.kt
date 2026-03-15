package com.example.network.module.download

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val album: String,
    val artist: String,
    val cover: String,
    val downloadLink: String,
    val id: String,
    val releaseDate: String,
    val title: String
)