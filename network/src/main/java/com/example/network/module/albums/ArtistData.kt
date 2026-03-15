package com.example.network.module.albums

import kotlinx.serialization.Serializable

@Serializable
data class ArtistData(
    val __typename: String,
    val profile: ProfileX,
    val uri: String
)