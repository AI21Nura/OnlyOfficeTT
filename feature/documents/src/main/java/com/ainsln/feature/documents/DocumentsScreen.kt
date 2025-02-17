package com.ainsln.feature.documents

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
import com.ainsln.core.model.storage.FileStorage
import com.ainsln.core.ui.component.AppBar
import com.ainsln.core.ui.component.EmptyScreen
import com.ainsln.core.ui.component.FileItem
import com.ainsln.core.ui.component.FolderItem
import com.ainsln.core.ui.component.RenderUiState
import com.ainsln.feature.documents.state.DocumentsUiState
import com.ainsln.feature.documents.state.DocumentsViewModel
import com.ainsln.core.ui.R as uiR

@Composable
internal fun DocumentsScreen(
    navigateToFolder: (Long, String) -> Unit,
    navigateUp: () -> Unit,
    viewModel: DocumentsViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsState()

    DocumentsScreenContent(
        uiState = uiState,
        navigateToFolder = navigateToFolder,
        navigateUp = navigateUp,
        errorAction = viewModel::loadDocuments
    )
}

@Composable
private fun DocumentsScreenContent(
    uiState: DocumentsUiState,
    navigateToFolder: (Long, String) -> Unit,
    navigateUp: () -> Unit,
    errorAction: () -> Unit
){
    Scaffold(
        topBar = {
            AppBar(
                title = uiState.folderTitle,
                navigateUp = if (uiState.canNavigateUp) navigateUp else null
            )
        }
    ) { innerPadding ->
        Box(Modifier.fillMaxSize().padding(innerPadding)){
            RenderUiState(
                uiState = uiState.storageState,
                crossfadeLabel = uiState.folderTitle,
                successContent = { StorageList(it, navigateToFolder) },
                errorAction = errorAction
            )
        }
    }
}

@Composable
private fun StorageList(
    storage: FileStorage,
    navigateToFolder: (Long, String) -> Unit
){
    if (storage.total == 0){
        EmptyScreen(
            icon = ImageVector.vectorResource(uiR.drawable.ic_folder_empty),
            message = stringResource(uiR.string.empty_folder_text),
            subtext = stringResource(uiR.string.empty_folder_subtext)
        )
    } else {
        LazyColumn {
            items(storage.folders){ folder ->
                FolderItem(folder, navigateToFolder)
            }
            items(storage.files){ file ->
                FileItem(file, {})
            }
        }
    }
}
