package com.ainsln.feature.trash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ainsln.core.model.storage.TrashStorage
import com.ainsln.core.ui.component.AppBar
import com.ainsln.core.ui.component.EmptyScreen
import com.ainsln.core.ui.component.FileItem
import com.ainsln.core.ui.component.FolderItem
import com.ainsln.core.ui.component.RenderUiState
import com.ainsln.core.ui.state.UiState

@Composable
internal fun TrashScreen(
    viewModel: TrashViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsState()
    TrashScreenContent(uiState, viewModel::loadTrash)
}

@Composable
private fun TrashScreenContent(
    uiState: UiState<TrashStorage>,
    errorAction: () -> Unit
){
    Scaffold(
        topBar = {
            AppBar(title = stringResource(R.string.trash))
        }
    ) { innerPadding ->
        Box(Modifier.fillMaxSize().padding(innerPadding)){
            RenderUiState(
                uiState = uiState,
                crossfadeLabel = "TrashScreen",
                successContent = { TrashList(it) },
                errorAction = errorAction
            )
        }
    }
}

@Composable
private fun TrashList(
    storage: TrashStorage
){
    if (storage.total == 0){
        EmptyScreen(
            icon = ImageVector.vectorResource(R.drawable.ic_trash_empty),
            message = stringResource(R.string.empty_trash_text),
            subtext = stringResource(R.string.empty_trash_subtext)
        )
    }
    LazyColumn(Modifier.fillMaxSize()) {
        items(storage.folders){ folder ->
            FolderItem(
                folder = folder.base,
                onFolderClick = {_, _ ->},
                subtitle = "Remainig days: ${folder.remainingDays}"
            )
        }
        items(storage.files){ file ->
            FileItem(
                file = file.base,
                onFileClick = {},
                subtitle = "Remainig days: ${file.remainingDays}"
            )
        }
    }
}
