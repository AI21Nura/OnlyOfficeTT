package com.ainsln.core.network.model.storage

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val id: String,
    val displayName: String,
    val avatar: String,
    val avatarOriginal: String,
    val avatarSmall: String,
    val profileUrl: String,
    val hasAvatar: Boolean,
    val isAnonim: Boolean,
)
