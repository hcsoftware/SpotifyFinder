package com.xr6software.spotifyfinder.model

data class Playlist(
    val id: String,
    val name: String,
    val description: String?,
    val images: List<PlaylistImage>
)


