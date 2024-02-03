package com.xr6software.spotifyfinder.usecases

import com.xr6software.spotifyfinder.data.SpotifyClient
import com.xr6software.spotifyfinder.model.Playlist

class GetFeaturedPlaylistsUseCase(
    private val spotifyClient: SpotifyClient
) {

    suspend fun execute(): List<Playlist> {
        return spotifyClient.getFeaturedPlaylists()
    }

}