package com.ainsln.core.ui.state

import com.ainsln.core.common.exception.AppException
import com.ainsln.core.common.result.DataResult

sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Error(val e: AppException) : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
}

fun <T> DataResult<T>.toUiState(): UiState<T>{
    return when(this){
        is DataResult.Loading -> UiState.Loading
        is DataResult.Failure -> UiState.Error(e)
        is DataResult.Success -> UiState.Success(data)
    }
}
