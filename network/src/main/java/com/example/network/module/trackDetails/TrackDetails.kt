package com.example.network.module.trackDetails

import kotlinx.serialization.Serializable


@Serializable
data class TrackDetails(
    val data: Tracks?,
    val success: Boolean = false,
    val message: String?
)