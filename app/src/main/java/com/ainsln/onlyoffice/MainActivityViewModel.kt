package com.ainsln.onlyoffice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainsln.core.data.repository.auth.AuthRepository
import com.ainsln.core.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    authRepository: AuthRepository
) : ViewModel() {
    val isAuthorized = authRepository.isAuthorized()
        .map { UiState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState.Loading
        )
}
