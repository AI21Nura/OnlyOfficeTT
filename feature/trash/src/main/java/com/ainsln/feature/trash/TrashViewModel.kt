package com.ainsln.feature.trash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainsln.core.data.repository.trash.TrashRepository
import com.ainsln.core.model.storage.TrashStorage
import com.ainsln.core.ui.state.UiState
import com.ainsln.core.ui.state.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrashViewModel @Inject constructor(
    private val trashRepository: TrashRepository
) : ViewModel(){

    private val _uiState: MutableStateFlow<UiState<TrashStorage>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadTrash()
    }

    fun loadTrash(){
        viewModelScope.launch {
            trashRepository.getTrash().map { it.toUiState() }
                .collectLatest { result ->
                    _uiState.update { result }
                }
        }
    }
}
