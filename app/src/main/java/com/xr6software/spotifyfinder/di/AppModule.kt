package com.xr6software.spotifyfinder.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloRequest
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.interceptor.ApolloInterceptor
import com.apollographql.apollo3.interceptor.ApolloInterceptorChain
import com.xr6software.spotifyfinder.data.ApolloSpotifyClient
import com.xr6software.spotifyfinder.data.SpotifyClient
import com.xr6software.spotifyfinder.usecases.GetFeaturedPlaylistsUseCase
import com.xr6software.spotifyfinder.usecases.GetPlaylistDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient() : ApolloClient {
        return ApolloClient.builder()
            .serverUrl("https://current--spotify-demo-graph-9olqwjc.apollographos.net/graphql")
            .addInterceptor(LoggingApolloInterceptor())
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

    class LoggingApolloInterceptor: ApolloInterceptor {
        override fun <D : Operation.Data> intercept(request: ApolloRequest<D>, chain: ApolloInterceptorChain): Flow<ApolloResponse<D>> {
            return chain.proceed(request).onEach { response ->
                println("Received response for ${request.operation.name()}: ${response.data}")
            }
        }
    }

}