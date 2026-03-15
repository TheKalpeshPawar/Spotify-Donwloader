package com.example.network.module.albums

import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val profile: Profile?,
    val uri: String?,
    val __typename: String?,
    val data: ArtistData?
)