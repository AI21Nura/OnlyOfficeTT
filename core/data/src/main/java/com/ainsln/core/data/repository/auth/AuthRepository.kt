package com.ainsln.core.data.repository.auth

import com.ainsln.core.common.result.DataResult
import com.ainsln.core.model.auth.AuthData
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(data: AuthData): Flow<DataResult<Unit>>
    fun logout(): Flow<DataResult<Unit>>
    fun isAuthorized(): Flow<Boolean>
}
