package com.ainsln.core.network

import com.ainsln.core.network.model.auth.AuthBody
import com.ainsln.core.network.model.profile.UserProfileDTO
import com.ainsln.core.network.model.workspace.WorkspaceDTO

interface NetworkDataSource {
    suspend fun authenticate(portal: String, authBody: AuthBody): Result<Unit>
    suspend fun logout(): Result<Unit>
    suspend fun getMyDocuments(): Result<WorkspaceDTO>
    suspend fun getTrash(): Result<WorkspaceDTO>
    suspend fun getRooms(): Result<WorkspaceDTO>
    suspend fun getMyProfile(): Result<UserProfileDTO>
}
