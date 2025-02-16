package com.ainsln.core.data.repository.profile

import com.ainsln.core.common.di.IODispatcher
import com.ainsln.core.common.exception.ExceptionHandler
import com.ainsln.core.common.result.asFlowResult
import com.ainsln.core.data.mapper.ProfileMapper
import com.ainsln.core.network.NetworkDataSource
import com.ainsln.core.network.utils.token.SessionManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class ProfileRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val sessionManager: SessionManager,
    private val mapper: ProfileMapper,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
    private val exceptionHandler: ExceptionHandler
) : ProfileRepository {

    override fun getMyProfile() = flow {
        val portal = sessionManager.getPortal(includeScheme = true).first()
        networkDataSource.getMyProfile()
            .onSuccess { emit(mapper.dtoToDomain(it, portal)) }
            .onFailure { e -> throw e }
    }.asFlowResult(dispatcher, exceptionHandler::handleException)
}
