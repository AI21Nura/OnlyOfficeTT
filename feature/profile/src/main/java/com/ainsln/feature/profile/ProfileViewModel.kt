package com.ainsln.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainsln.core.data.repository.auth.AuthRepository
import com.ainsln.core.data.repository.profile.ProfileRepository
import com.ainsln.core.model.profile.UserProfile
import com.ainsln.core.ui.state.UiState
import com.ainsln.core.ui.state.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<UserProfile>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    fun loadProfile(){
        viewModelScope.launch {
            profileRepository.getMyProfile().map { it.toUiState() }
                .collectLatest { result ->
                _uiState.update { result }
            }
        }
    }

    fun logout(){
        viewModelScope.launch {
            authRepository.logout().collect()
        }
    }
}
