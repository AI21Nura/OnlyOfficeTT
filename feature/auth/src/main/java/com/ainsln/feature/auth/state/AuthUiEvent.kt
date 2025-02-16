package com.ainsln.feature.auth.state

sealed interface AuthUiEvent {
    data class ShowSnackbarError(val msg: String) : AuthUiEvent
}
