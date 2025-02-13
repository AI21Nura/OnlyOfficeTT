package com.ainsln.core.network.retrofit

import com.ainsln.core.network.NetworkDataSource
import com.ainsln.core.network.model.auth.AuthBody
import com.ainsln.core.network.model.unwrapResponse
import com.ainsln.core.network.utils.token.SessionManager
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
    private val retrofitApi: RetrofitApi,
    private val sessionManager: SessionManager
) : NetworkDataSource {

    override suspend fun authenticate(
        portal: String,
        authBody: AuthBody
    ): Result<Unit> {
        return runCatching {
            val url = "$portal/api/2.0/authentication"
            val data = retrofitApi.authenticate(url, authBody)
            sessionManager.saveAuthData(portal, data.response.token)
        }
    }

    override suspend fun logout(): Result<Unit> {
        return runCatching {
            retrofitApi.logout()
            sessionManager.deleteAuthData()
        }
    }

    override suspend fun getMyDocuments() = retrofitApi.getMyDocuments().unwrapResponse()
    override suspend fun getTrash() = retrofitApi.getTrash().unwrapResponse()
    override suspend fun getRooms() = retrofitApi.getRooms().unwrapResponse()
    override suspend fun getMyProfile() = retrofitApi.getMyProfile().unwrapResponse()
}
