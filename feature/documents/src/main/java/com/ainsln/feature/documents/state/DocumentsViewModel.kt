package com.ainsln.feature.documents.state

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ainsln.core.data.repository.document.DocumentRepository
import com.ainsln.core.ui.utils.ResourceManager
import com.ainsln.core.ui.state.toUiState
import com.ainsln.feature.documents.R
import com.ainsln.feature.documents.navigation.DocumentsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor(
    private val documentRepository: DocumentRepository,
    private val resourceManager: ResourceManager,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val navInfo = savedStateHandle.toRoute<DocumentsDestination>()

    private val _uiState: MutableStateFlow<DocumentsUiState> = MutableStateFlow(setupAppBarOptions())
    val uiState = _uiState.asStateFlow()

    init {
        loadDocuments()
    }

    fun loadDocuments() {
        viewModelScope.launch {
            documentRepository.getMyDocuments(navInfo.folderId).map { it.toUiState() }
                .collectLatest { result ->
                    _uiState.update { it.copy(storageState = result) }
                }
        }
    }

    private fun setupAppBarOptions(): DocumentsUiState {
        val title = navInfo.folderTitle ?: resourceManager.getString(R.string.my_documents)
        val canNavigateUp = navInfo.folderId != null
        return (DocumentsUiState(folderTitle = title, canNavigateUp = canNavigateUp))
    }
}
