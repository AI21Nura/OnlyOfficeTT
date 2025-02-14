package com.ainsln.core.common.exception

sealed interface AppException {
    data object AuthException : AppException
    data object NoConnectionException : AppException
    data object InvalidResponseException : AppException
    data object NotFoundException : AppException
    data object UnknownException : AppException
}
