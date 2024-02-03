package com.xr6software.spotifyfinder.data

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.xr6software.FeaturedPlaylistsQuery
import com.xr6software.SearchPlaylistQuery
import com.xr6software.spotifyfinder.model.DetailPlaylist
import com.xr6software.spotifyfinder.model.Playlist


class ApolloSpotifyClient (private val apolloClient: ApolloClient) : SpotifyClient {

    override suspend fun getFeaturedPlaylists() : List<Playlist> {

        return try {
            apolloClient.query(FeaturedPlaylistsQuery()).execute()
                .data
                ?.featuredPlaylists
                ?.edges
                ?.map {
                    it.node.toPlaylist()
                } ?: emptyList()

        } catch (e: Exception) {
            logError(e)
            emptyList()
        }

    }

    override suspend fun getPlaylistDetails(playlistId: String): DetailPlaylist? {
        return try {
            apolloClient.query(SearchPlaylistQuery(playlistId)).execute()
                .data
                ?.playlist
                ?.toDetailPlaylist()
        } catch (e: Exception) {
        logError(e)
        null
    }

    }

    private fun logError(e: Exception) {
        Log.e(Consts.LOGGING_TAG,"There's an error with GraphQl check config -> $e   >message ${e.message}  ${e.localizedMessage}   ${e.stackTrace}  cause ${e.cause}")
    }

}