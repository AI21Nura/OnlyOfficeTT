package com.ainsln.core.data.repository.profile

import com.ainsln.core.common.result.DataResult
import com.ainsln.core.model.profile.UserProfile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getMyProfile(): Flow<DataResult<UserProfile>>
}
