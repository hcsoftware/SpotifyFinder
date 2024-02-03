package com.xr6software.spotifyfinder.di

import com.apollographql.apollo3.ApolloClient
import com.xr6software.spotifyfinder.data.ApolloClientLogger
import com.xr6software.spotifyfinder.data.ApolloSpotifyClient
import com.xr6software.spotifyfinder.data.Consts
import com.xr6software.spotifyfinder.data.SpotifyClient
import com.xr6software.spotifyfinder.usecases.GetFeaturedPlaylistsUseCase
import com.xr6software.spotifyfinder.usecases.GetPlaylistDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient() : ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(Consts.SPOTIFY_GRAPHQL_SERVER_URL)
            .addInterceptor(ApolloClientLogger())
            .build()
    }

    @Provides
    @Singleton
    fun provideSpotifyClient(apolloClient: ApolloClient) : SpotifyClient {
        return ApolloSpotifyClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetFeaturedPlaylistUseCase(spotifyClient: SpotifyClient) : GetFeaturedPlaylistsUseCase {
        return GetFeaturedPlaylistsUseCase(spotifyClient)
    }

    @Provides
    @Singleton
    fun provideGetPlaylistDetailUseCase(spotifyClient: SpotifyClient) : GetPlaylistDetailUseCase {
        return GetPlaylistDetailUseCase(spotifyClient)
    }


}