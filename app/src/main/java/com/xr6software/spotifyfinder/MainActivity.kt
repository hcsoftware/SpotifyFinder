package com.xr6software.spotifyfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.xr6software.spotifyfinder.presentation.MainActivityMainScreen
import com.xr6software.spotifyfinder.presentation.MainActivityViewModel
import com.xr6software.spotifyfinder.ui.theme.SpotifyFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SpotifyFinderTheme {
                val viewModel = hiltViewModel<MainActivityViewModel>()
                val state by viewModel.viewState.collectAsState()
                // A surface container using the 'background' color from the theme -> could use Scaffold
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainActivityMainScreen(state = state,
                        onPlaylistSelected = viewModel::getPlaylistDetail
                        ,
                        onDismissPlaylistDialog = viewModel::dismissDialog
                    )
                }
            }
        }
    }
}
