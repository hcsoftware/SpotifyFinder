package com.xr6software.spotifyfinder.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.xr6software.spotifyfinder.R
import com.xr6software.spotifyfinder.model.DetailPlaylist
import com.xr6software.spotifyfinder.model.Playlist
import com.xr6software.spotifyfinder.ui.theme.BorderColor
import com.xr6software.spotifyfinder.ui.theme.HeaderBackgroundColor
import com.xr6software.spotifyfinder.ui.theme.ListItemColor


@Composable
fun MainActivityMainScreen(
    state: MainActivityViewModel.MainViewModelState,
    onPlaylistSelected: (playlistId: String) -> Unit,
    onDismissPlaylistDialog: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MainActivityHeaderScreen()
        BorderStroke()
        MainActivityBodyScreen(
            state = state,
            onPlaylistSelected = onPlaylistSelected,
            onDismissPlaylistDialog = onDismissPlaylistDialog
        )
    }

}

@Composable
fun MainActivityHeaderScreen() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(HeaderBackgroundColor),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.spotify_logo),
            contentDescription = "Media Logo"
        )

    }
}

@Composable
fun BorderStroke() {
    Divider(
        color = BorderColor, modifier = Modifier
            .height(2.dp)
            .fillMaxWidth()
    )
}

@Composable
fun MainActivityBodyScreen(

    state: MainActivityViewModel.MainViewModelState,
    onPlaylistSelected: (playlistId: String) -> Unit,
    onDismissPlaylistDialog: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(100.dp),
                strokeWidth = 9.dp,
                color =  BorderColor

            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.featuredPlaylist) { playList ->
                    PlaylistItem(playlist = playList, modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onPlaylistSelected(playList.id)
                        })

                }
            }
            if (state.playlistDetail != null) {
                PlaylistDialog(
                    playlist = state.playlistDetail,
                    onDismiss = onDismissPlaylistDialog,
                    modifier = Modifier.align(Alignment.Center)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.LightGray)
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }

    }


}


@Composable
private fun PlaylistItem(
    playlist: Playlist, modifier: Modifier
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = modifier.background(ListItemColor),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp),
                model = playlist.images[0].url,
                contentDescription = "Playlist Image"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = playlist.name, fontSize = 20.sp)
                Spacer(modifier = Modifier.width(16.dp))
                println("description = ${playlist.description}")
                Text(text = (if (playlist.description.isNullOrEmpty()) { "No description"} else playlist.description), fontSize = 15.sp)
            }

        }
        BorderStroke()
    }


}

@Composable
private fun PlaylistDialog(
    playlist: DetailPlaylist,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column (
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = playlist.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(model = playlist.images[0].url, contentDescription = "")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Owner: ${playlist.owner.displayName}", fontSize = 12.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = playlist.description, fontSize = 12.sp)
        }
    }

}
