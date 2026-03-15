package com.example.network.module.tracks

import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val profile: Profile,
    val uri: String
)