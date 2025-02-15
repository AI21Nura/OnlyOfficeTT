package com.ainsln.core.network

import com.ainsln.core.network.model.auth.AuthBody
import com.ainsln.core.network.model.profile.UserProfileDTO
import com.ainsln.core.network.model.storage.StorageDTO

interface NetworkDataSource {
    suspend fun authenticate(portal: String, authBody: AuthBody): Result<Unit>
    suspend fun logout(): Result<Unit>
    suspend fun getMyDocuments(): Result<StorageDTO>
    suspend fun getFolderById(id: Long): Result<StorageDTO>
    suspend fun getTrash(): Result<StorageDTO>
    suspend fun getRooms(): Result<StorageDTO>
    suspend fun getMyProfile(): Result<UserProfileDTO>
}
