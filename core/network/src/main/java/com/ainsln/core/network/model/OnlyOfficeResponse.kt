package com.ainsln.core.network.model

import com.ainsln.core.network.model.auth.AuthResponseData
import com.ainsln.core.network.model.profile.UserProfileDTO
import com.ainsln.core.network.model.storage.StorageDTO
import kotlinx.serialization.Serializable

@Serializable
data class OnlyOfficeResponse<T>(
    val response: T,
    val count: Int,
    val status: Int,
    val statusCode: Int,
    val links: List<Link>
)

@Serializable
data class Link(
    val href: String,
    val action: String
)

typealias AuthResponse = OnlyOfficeResponse<AuthResponseData>
typealias UserProfileResponse = OnlyOfficeResponse<UserProfileDTO>
typealias StorageResponse = OnlyOfficeResponse<StorageDTO>
