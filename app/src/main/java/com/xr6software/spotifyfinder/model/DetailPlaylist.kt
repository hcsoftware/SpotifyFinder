package com.xr6software.spotifyfinder.model

data class DetailPlaylist(
    val name: String,
    val owner: PlaylistOwner,
    val description: String,
    val images: List<PlaylistImage>
)

