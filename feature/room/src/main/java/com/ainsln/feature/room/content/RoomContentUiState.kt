package com.ainsln.feature.room.content

import com.ainsln.core.model.storage.FileStorage
import com.ainsln.core.ui.state.UiState

data class RoomContentUiState(
    val roomTitle: String = "",
    val folderTitle: String? = null,
    val contentState: UiState<FileStorage> = UiState.Loading
)
