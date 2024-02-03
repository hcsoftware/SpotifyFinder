package com.xr6software.spotifyfinder.data

import com.xr6software.FeaturedPlaylistsQuery
import com.xr6software.SearchPlaylistQuery
import com.xr6software.spotifyfinder.model.DetailPlaylist
import com.xr6software.spotifyfinder.model.Playlist
import com.xr6software.spotifyfinder.model.PlaylistImage
import com.xr6software.spotifyfinder.model.PlaylistOwner


fun FeaturedPlaylistsQuery.Node.toPlaylist() = Playlist(
    id = this.id,
    description = this.description,
    name = this.name,
    images = this.images.map { it.toPlaylistImage() }
)

fun FeaturedPlaylistsQuery.Image.toPlaylistImage() = PlaylistImage(
    url = this.url
)

fun SearchPlaylistQuery.Playlist.toDetailPlaylist() = DetailPlaylist(
    name = this.name,
    description = this.description ?: "No description",
    images = this.images.map { it.toPlaylistImage() },
    owner = this.owner.toOwner()
)

fun SearchPlaylistQuery.Image.toPlaylistImage() = PlaylistImage(
    url = this.url
)

fun SearchPlaylistQuery.Owner.toOwner() = PlaylistOwner(
    displayName = this.displayName ?: "No owner."
)

