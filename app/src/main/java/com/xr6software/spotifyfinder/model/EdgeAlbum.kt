package com.xr6software.spotifyfinder.model

import com.xr6software.NewReleasesQuery

data class EdgeAlbum(
    val name: String,
    val id: String,
    val images: List<NewReleasesQuery.Image>
)
