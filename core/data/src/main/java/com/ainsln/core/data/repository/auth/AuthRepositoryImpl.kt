package com.ainsln.core.data.repository.auth

import com.ainsln.core.common.result.DataResult
import com.ainsln.core.data.mapper.AuthMapper
import com.ainsln.core.data.utils.NetworkExecutor
import com.ainsln.core.model.auth.AuthData
import com.ainsln.core.network.NetworkDataSource
import com.ainsln.core.network.utils.token.SessionManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val sessionManager: SessionManager,
    private val mapper: AuthMapper,
    private val networkExecutor: NetworkExecutor
) : AuthRepository {

    override fun login(data: AuthData): Flow<DataResult<Unit>> {
        return networkExecutor.execute(
            networkCall = {
                authenticate(
                    portal = normalizePortal(data.portal),
                    authBody = mapper.domainToDto(data)
                )
            },
            mapper = {}
        )
    }

    override fun logout(): Flow<DataResult<Unit>> {
        return networkExecutor.execute(
            networkCall = NetworkDataSource::logout,
            mapper = {}
        )
    }

    override fun isAuthorized(): Flow<Boolean> {
        return sessionManager.isAuthorized()
    }

    private fun normalizePortal(input: String): String {
        var portal = input.removeSuffix("/")
        if (!portal.startsWith(SessionManager.BASE_SCHEME)) {
            portal = SessionManager.BASE_SCHEME + portal
        }
        return portal
    }
}
