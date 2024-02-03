package com.xr6software.spotifyfinder.data

import com.apollographql.apollo3.ApolloClient
import com.xr6software.EdgesQuery
import com.xr6software.FeaturedPlaylistsQuery
import com.xr6software.SearchPlaylistQuery
import com.xr6software.spotifyfinder.model.DetailPlaylist
import com.xr6software.spotifyfinder.model.EdgeAlbum
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
            println("There's an error with GraphQl check config -> $e   >message ${e.message}  ${e.localizedMessage}   ${e.stackTrace}  cause ${e.cause}")
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
        println("There's an error with GraphQl check config -> $e   >message ${e.message}  ${e.localizedMessage}   ${e.stackTrace}  cause ${e.cause}")
        null
    }

    }

    override suspend fun getNewReleases(limit: Int, offset: Int): List<EdgeAlbum>? {


        try {

            val response = apolloClient.query(EdgesQuery(10)).execute()

            println("LOGHC apollo response -> ${response.data}")

            val responseTwo = apolloClient.query(FeaturedPlaylistsQuery()).execute()


            println("LOGHC apollo responseTwo -> ${responseTwo.data}")



            //response =  apolloClient.query()

            // handle response and return it
        } catch (e: Exception) {
            // handle error and return response

            println("LOGHC $e   >message ${e.message}  ${e.localizedMessage}   ${e.stackTrace}  cause ${e.cause}")
        }

        //println("LOGHC ERROR IS $error")


        //println("something $something")

        /*
        apolloClient
            .query(NewReleasesQuery())
            .execute()
            .data
            //?.newReleases?.edges
            //?.map {
            //    println("LOGHC inside map")
            //    it.node.toEdgeAlbum()
            //}

         */

        println("LOGHC post apollo client")

        return emptyList()
    }

    override suspend fun getDiscInformation(): String {
        TODO("Not yet implemented")
    }

}