package com.ainsln.core.data.repository.profile

import com.ainsln.core.common.result.DataResult
import com.ainsln.core.data.mapper.ProfileMapper
import com.ainsln.core.data.utils.NetworkExecutor
import com.ainsln.core.model.profile.UserProfile
import com.ainsln.core.network.utils.token.SessionManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class ProfileRepositoryImpl @Inject constructor(
    private val sessionManager: SessionManager,
    private val mapper: ProfileMapper,
    private val networkExecutor: NetworkExecutor
) : ProfileRepository {

    override fun getMyProfile(): Flow<DataResult<UserProfile>> {
        return networkExecutor.execute(
            networkCall = { getMyProfile() },
            mapper = {
                val portal = sessionManager.getPortal(includeScheme = true).first()
                mapper.dtoToDomain(it, portal)
            }
        )
    }
}
