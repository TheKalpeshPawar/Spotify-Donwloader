package com.example.network.module.artists

data class Artist(
    val avatarImg: List<AvatarImg>,
    val id: String,
    val profile: Profile,
    val type: String,
    val uri: String
)