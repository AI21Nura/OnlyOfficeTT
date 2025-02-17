package com.ainsln.feature.documents.state

import com.ainsln.core.model.storage.FileStorage
import com.ainsln.core.ui.state.UiState

data class DocumentsUiState(
    val folderTitle: String = "",
    val canNavigateUp: Boolean = false,
    val storageState: UiState<FileStorage> = UiState.Loading
)
