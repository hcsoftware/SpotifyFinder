package com.xr6software.spotifyfinder.data

import com.xr6software.spotifyfinder.model.DetailPlaylist
import com.xr6software.spotifyfinder.model.Playlist

interface SpotifyClient {

    suspend fun getFeaturedPlaylists() : List<Playlist>

    suspend fun getPlaylistDetails(playlistId: String) : DetailPlaylist?

}