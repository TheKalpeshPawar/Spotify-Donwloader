package com.example.network.module.trackDetails.trackRecommendations

import com.example.network.module.trackDetails.Track
import kotlinx.serialization.Serializable


@Serializable
data class Recommendations(
    val tracks: List<Track>
)