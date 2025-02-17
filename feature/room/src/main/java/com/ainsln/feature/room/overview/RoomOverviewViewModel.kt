package com.ainsln.feature.room.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainsln.core.data.repository.room.RoomRepository
import com.ainsln.core.model.storage.RoomStorage
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
class RoomOverviewViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel(){

    private val _uiState: MutableStateFlow<UiState<RoomStorage>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadRooms()
    }

    fun loadRooms(){
        viewModelScope.launch {
            roomRepository.getRooms().map { it.toUiState() }
                .collectLatest { result ->
                    _uiState.update { result }
                }
        }
    }
}
