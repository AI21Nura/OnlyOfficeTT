package com.ainsln.feature.room.overview

import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.ainsln.core.model.storage.RoomStorage
import com.ainsln.core.model.storage.room.Room
import com.ainsln.core.ui.component.AppBar
import com.ainsln.core.ui.component.EmptyScreen
import com.ainsln.core.ui.component.RenderUiState
import com.ainsln.core.ui.component.StorageItem
import com.ainsln.core.ui.state.UiState
import com.ainsln.feature.room.R
import com.ainsln.feature.room.navigation.RoomContentArgs
import com.ainsln.core.ui.R as uiR

@Composable
internal fun RoomOverviewScreen(
    navigateToRoom: (RoomContentArgs) -> Unit,
    viewModel: RoomOverviewViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    RoomOverviewScreenContent(
        uiState = uiState,
        errorAction = viewModel::loadRooms,
        navigateToRoom = navigateToRoom
    )
}

@Composable
private fun RoomOverviewScreenContent(
    uiState: UiState<RoomStorage>,
    errorAction: () -> Unit,
    navigateToRoom: (RoomContentArgs) -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(title = stringResource(R.string.rooms))
        }
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            RenderUiState(
                uiState = uiState,
                crossfadeLabel = "RoomScreen",
                successContent = { RoomsList(it.rooms, navigateToRoom) },
                errorAction = errorAction
            )
        }
    }
}

@Composable
private fun RoomsList(
    rooms: List<Room>,
    navigateToRoom: (RoomContentArgs) -> Unit
) {
    if (rooms.isEmpty()) {
        EmptyScreen(
            icon = ImageVector.vectorResource(uiR.drawable.ic_folder_empty),
            message = stringResource(R.string.empty_rooms_list),
            subtext = stringResource(R.string.empty_rooms_list_subtext)
        )
    } else {
        LazyColumn {
            items(rooms) { room ->
                RoomItem(room, navigateToRoom)
            }
        }
    }
}

@Composable
private fun RoomItem(
    room: Room,
    navigateToRoom: (RoomContentArgs) -> Unit
) {
    StorageItem(
        onItemClick = {
            navigateToRoom(
                RoomContentArgs(
                    roomTitle = room.base.title,
                    folderId = room.base.id,
                    folderTitle = null
                )
            )
        },
        icon = {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(room.logo.original)
                    .addHeader("Authorization", room.logo.token ?: "")
                    .crossfade(true)
                    .build(),
                loading = { DefaultRoomLogo(room.logo.color) },
                error = { DefaultRoomLogo(room.logo.color) },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        },
        title = room.base.title
    )
}

@Composable
private fun DefaultRoomLogo(
    logoColor: String? = null
) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(
                color = parseLogoColor(logoColor),
                shape = RoundedCornerShape(8.dp)
            )
    )
}

private fun parseLogoColor(color: String?): Color {
    return try {
        color?.let { Color(parseColor(color)) } ?: Color.LightGray
    } catch (e: Throwable) {
        Color.LightGray
    }
}
