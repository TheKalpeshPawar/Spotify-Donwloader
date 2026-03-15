package com.example.network.module.trackDetails

import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val id: String,
    val name: String,
)