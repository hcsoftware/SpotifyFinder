package com.xr6software.spotifyfinder.usecases

import com.xr6software.spotifyfinder.data.SpotifyClient
import com.xr6software.spotifyfinder.model.DetailPlaylist

class GetPlaylistDetailUseCase (
        private val spotifyClient: SpotifyClient
    )
    {

        suspend fun execute(playlistId: String): DetailPlaylist? {
            return spotifyClient.getPlaylistDetails(playlistId)
        }

    }