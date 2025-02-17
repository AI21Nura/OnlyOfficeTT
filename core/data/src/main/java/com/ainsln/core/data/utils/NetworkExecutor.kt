package com.ainsln.core.data.utils

import com.ainsln.core.common.di.IODispatcher
import com.ainsln.core.common.exception.ExceptionHandler
import com.ainsln.core.common.result.DataResult
import com.ainsln.core.common.result.asFlowResult
import com.ainsln.core.network.NetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface NetworkExecutor {
    fun <T, R> execute(
        networkCall: suspend NetworkDataSource.() -> Result<T>,
        mapper: suspend (T) -> R
    ) : Flow<DataResult<R>>
}

class BaseNetworkExecutor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
    private val exceptionHandler: ExceptionHandler
) : NetworkExecutor {

    override fun <T, R> execute(
        networkCall: suspend NetworkDataSource.() -> Result<T>,
        mapper: suspend (T) -> R
    ) : Flow<DataResult<R>> = flow {
        networkDataSource.networkCall()
            .onSuccess { emit(mapper(it)) }
            .onFailure { e -> throw e }
    }.asFlowResult(dispatcher, exceptionHandler::handleException)
}
