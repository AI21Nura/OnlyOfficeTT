package com.ainsln.feature.room.content

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ainsln.core.data.repository.document.DocumentRepository
import com.ainsln.core.ui.state.toUiState
import com.ainsln.feature.room.navigation.RoomContentArgs
import com.ainsln.feature.room.navigation.RoomContentDestination
import com.ainsln.feature.room.navigation.parcelableType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.typeOf

@HiltViewModel
class RoomContentViewModel  @Inject constructor(
    private val documentRepository: DocumentRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val navInfo = savedStateHandle.toRoute<RoomContentDestination>(
        typeMap = mapOf(typeOf<RoomContentArgs>() to parcelableType<RoomContentArgs>())
    ).args

    private val _uiState: MutableStateFlow<RoomContentUiState> = MutableStateFlow(setupAppBarOptions())
    val uiState = _uiState.asStateFlow()

    init {
        loadContent()
    }

    fun loadContent() {
        viewModelScope.launch {
            documentRepository.getMyDocuments(navInfo.folderId).map { it.toUiState() }
                .collectLatest { result ->
                    _uiState.update { it.copy(contentState = result) }
                }
        }
    }

    private fun setupAppBarOptions(): RoomContentUiState {
        return RoomContentUiState(
            roomTitle = navInfo.roomTitle,
            folderTitle = navInfo.folderTitle
        )
    }
}
