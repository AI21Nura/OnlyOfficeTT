package com.ainsln.core.data.repository.auth

import com.ainsln.core.common.di.IODispatcher
import com.ainsln.core.common.exception.ExceptionHandler
import com.ainsln.core.common.result.DataResult
import com.ainsln.core.common.result.asFlowResult
import com.ainsln.core.data.mapper.AuthMapper
import com.ainsln.core.model.auth.AuthData
import com.ainsln.core.network.NetworkDataSource
import com.ainsln.core.network.utils.token.SessionManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val sessionManager: SessionManager,
    private val mapper: AuthMapper,
    private val exceptionHandler: ExceptionHandler,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : AuthRepository {

    override fun login(data: AuthData): Flow<DataResult<Unit>> {
        return handleUnitResult {
            networkDataSource.authenticate(
                portal = normalizePortal(data.portal),
                authBody = mapper.domainToDto(data)
            )
        }
    }

    override fun logout(): Flow<DataResult<Unit>> {
        return handleUnitResult { networkDataSource.logout() }
    }

    override fun isAuthorized(): Flow<Boolean> {
        return sessionManager.isAuthorized()
    }

    private fun handleUnitResult(block: suspend () -> Result<Unit>): Flow<DataResult<Unit>> =
        flow {
            block()
                .onSuccess { data -> emit(data) }
                .onFailure { e -> throw e }
        }.asFlowResult(dispatcher, exceptionHandler::handleException)

    private fun normalizePortal(input: String): String {
        var portal = input.removeSuffix("/")
        if (!portal.startsWith("https://")) {
            portal = "https://$portal"
        }
        return portal
    }
}
