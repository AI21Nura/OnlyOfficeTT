package com.ainsln.core.common.exception

import com.ainsln.core.common.utils.Logger
import javax.inject.Inject

interface ExceptionHandler {
    fun handleException(e: Throwable): AppException
}

internal class BaseExceptionHandler @Inject constructor(
    private val logger: Logger
) : ExceptionHandler {

    override fun handleException(e: Throwable): AppException {
        val (appException, cause) = when(e) {
            is NetworkException -> handleNetworkException(e) to e.cause
            else -> AppException.UnknownException to e
        }
        val exceptionType = e.javaClass.simpleName
        logger.e(TAG, "$exceptionType caused: $cause")
        return appException
    }

    private fun handleNetworkException(e: NetworkException): AppException{
        return when(e) {
            is NetworkException.NoConnectionException -> AppException.NoConnectionException
            is NetworkException.JsonDecodingException -> AppException.InvalidResponseException
            is NetworkException.UnknownNetworkException -> AppException.NotFoundException
            is NetworkException.HttpException ->
                if (e.code == 401) AppException.AuthException
                else AppException.NotFoundException
        }
    }

    companion object {
        private const val TAG = "BaseExceptionHandler"
    }
}
