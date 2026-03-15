package com.example.network.module.trackDetails.trackRecommendations

import kotlinx.serialization.Serializable

@Serializable
data class TrackRecommendations(
    val data: Recommendations?,
    val success: Boolean,
    val message: String?
)