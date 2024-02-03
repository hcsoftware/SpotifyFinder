package com.xr6software.spotifyfinder.data

import com.xr6software.AlbumQuery
import com.xr6software.FeaturedPlaylistsQuery
import com.xr6software.NewReleasesQuery
import com.xr6software.SearchPlaylistQuery
import com.xr6software.spotifyfinder.model.AlbumDetail
import com.xr6software.spotifyfinder.model.DetailPlaylist
import com.xr6software.spotifyfinder.model.EdgeAlbum
import com.xr6software.spotifyfinder.model.Playlist
import com.xr6software.spotifyfinder.model.PlaylistImage
import com.xr6software.spotifyfinder.model.PlaylistOwner

fun AlbumQuery.Album.toAlbumDetail() = AlbumDetail(
    name = this.name,
    genres = this.genres
)


fun NewReleasesQuery.Node.toEdgeAlbum() = EdgeAlbum(

    name = this.name,
    id = this.id,
    images = this.images

)

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

