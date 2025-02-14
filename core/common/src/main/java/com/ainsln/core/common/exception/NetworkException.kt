package com.ainsln.core.common.exception

sealed class NetworkException(override val cause: Throwable? = null) : Throwable(cause) {
    class HttpException(val code: Int, cause: Throwable?) : NetworkException(cause)
    class JsonDecodingException(cause: Throwable?) : NetworkException(cause)
    class NoConnectionException : NetworkException()
    class UnknownNetworkException(cause: Throwable?) : NetworkException(cause)
}
