package com.ainsln.core.network.utils

import com.ainsln.core.common.exception.NetworkException
import com.ainsln.core.network.model.OnlyOfficeResponse
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException

internal fun <T> Result<OnlyOfficeResponse<T>>.unwrapResponse(): Result<T> =
    mapCatching { it.response }.wrapNetworkException()

internal fun <T> Result<T>.wrapNetworkException() : Result<T> {
    return recoverCatching { e ->
        val networkException = when(e){
            is HttpException -> NetworkException.HttpException(e.code(), e)
            is SerializationException -> NetworkException.JsonDecodingException(e)
            is IOException -> NetworkException.NoConnectionException()
            else -> NetworkException.UnknownNetworkException(e)
        }
        return Result.failure(networkException)
    }
}
