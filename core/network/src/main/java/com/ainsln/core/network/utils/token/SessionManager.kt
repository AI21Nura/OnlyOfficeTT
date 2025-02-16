package com.ainsln.core.network.utils.token

import kotlinx.coroutines.flow.Flow

interface SessionManager {
    fun isAuthorized(): Flow<Boolean>
    suspend fun saveAuthData(portal: String, token: String)
    suspend fun deleteAuthData()
    fun getToken(): Flow<String>
    fun getPortal(includeScheme: Boolean = false): Flow<String>
}
