package com.ainsln.feature.room.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ainsln.core.model.storage.FileStorage
import com.ainsln.core.ui.component.AppBar
import com.ainsln.core.ui.component.EmptyScreen
import com.ainsln.core.ui.component.FileItem
import com.ainsln.core.ui.component.FolderItem
import com.ainsln.core.ui.component.RenderUiState
import com.ainsln.feature.room.navigation.RoomContentArgs
import com.ainsln.core.ui.R as uiR

@Composable
internal fun RoomContentScreen(
    navigateToContent: (RoomContentArgs) -> Unit,
    navigateUp: () -> Unit,
    viewModel: RoomContentViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    RoomContentScreenContent(
        uiState = uiState,
        navigateToContent = navigateToContent,
        navigateUp = navigateUp,
        errorAction = viewModel::loadContent
    )
}

@Composable
private fun RoomContentScreenContent(
    uiState: RoomContentUiState,
    navigateToContent: (RoomContentArgs) -> Unit,
    navigateUp: () -> Unit,
    errorAction: () -> Unit
) {
    Scaffold(
        topBar = {
            AppBar(
                title = uiState.roomTitle,
                navigateUp = navigateUp
            )
        }
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            RenderUiState(
                uiState = uiState.contentState,
                crossfadeLabel = "$uiState.roomTitle ${uiState.folderTitle}",
                successContent = { data ->
                    StorageList(
                        storage = data,
                        navigateToContent = navigateToContent,
                        roomTitle = uiState.roomTitle,
                        folderTitle = uiState.folderTitle
                    )
                },
                errorAction = errorAction
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StorageList(
    storage: FileStorage,
    navigateToContent: (RoomContentArgs) -> Unit,
    roomTitle: String,
    folderTitle: String? = null
) {
    Scaffold(
        topBar = {
            if (folderTitle != null) {
                TopAppBar(title = { Text(folderTitle) })
            }
        }
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (storage.total == 0) {
                EmptyScreen(
                    icon = ImageVector.vectorResource(uiR.drawable.ic_folder_empty),
                    message = stringResource(uiR.string.empty_folder_text),
                    subtext = stringResource(uiR.string.empty_folder_subtext)
                )
            } else {
                LazyColumn {
                    items(storage.folders) { folder ->
                        FolderItem(
                            folder = folder,
                            onFolderClick = { folderId, folderTitle ->
                                navigateToContent(
                                    RoomContentArgs(
                                        roomTitle = roomTitle,
                                        folderId = folderId,
                                        folderTitle = folderTitle
                                    )
                                )

                            })
                    }
                    items(storage.files) { file ->
                        FileItem(file, {})
                    }
                }
            }
        }
    }
}
