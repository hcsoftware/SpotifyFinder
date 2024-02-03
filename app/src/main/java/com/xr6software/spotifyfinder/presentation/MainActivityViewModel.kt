package com.xr6software.spotifyfinder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xr6software.spotifyfinder.model.DetailPlaylist
import com.xr6software.spotifyfinder.model.EdgeAlbum
import com.xr6software.spotifyfinder.model.Playlist
import com.xr6software.spotifyfinder.usecases.GetFeaturedPlaylistsUseCase
import com.xr6software.spotifyfinder.usecases.GetPlaylistDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getFeaturedPlaylistsUseCase: GetFeaturedPlaylistsUseCase,
    private val getPlaylistDetailUseCase: GetPlaylistDetailUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainViewModelState())
    val viewState = _viewState.asStateFlow()

    data class MainViewModelState (
        val isLoading : Boolean = false,
        val playlistDetail: DetailPlaylist? = null,
        val topDiscs : List<String> = emptyList(),
        val topArtists: List<String> = emptyList(),
        val newReleases: List<EdgeAlbum> = emptyList(),
        val featuredPlaylist: List<Playlist> = emptyList(),
        val selectedDisc: String? = null

    )

    init {
        viewModelScope.launch {

            _viewState.update {
                it.copy(
                    isLoading = true
                )
            }
            _viewState.update {
                it.copy(
                    featuredPlaylist = getFeaturedPlaylistsUseCase.execute(),
                    isLoading = false
                )
            }

        }

    }

    fun getPlaylistDetail(playlistId: String) {
        viewModelScope.launch {
            _viewState.update {
                it.copy(
                    isLoading = true
                )
            }
            _viewState.update {
                it.copy(
                    playlistDetail = getPlaylistDetailUseCase.execute(playlistId),
                    isLoading = false
                )
            }
        }
    }

    fun dismissDialog() {
        _viewState.update {
            it.copy(
                playlistDetail = null
            )
        }
    }

}
