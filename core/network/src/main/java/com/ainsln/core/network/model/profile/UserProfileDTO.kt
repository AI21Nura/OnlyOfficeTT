package com.ainsln.core.network.model.profile

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileDTO(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val email: String,
    val status: Int,
    val activationStatus: Int,
    val department: String,
    val workFrom: String,
    val isAdmin: Boolean,
    val isRoomAdmin: Boolean,
    val isLDAP: Boolean,
    val isOwner: Boolean,
    val isVisitor: Boolean,
    val isCollaborator: Boolean,
    val cultureName: String? = null,
    val mobilePhoneActivationStatus: Int,
    val isSSO: Boolean,
    val theme: String,
    val loginEventId: Int,
    val registrationDate: String,
    val id: String,
    val displayName: String,
    val avatar: String,
    val avatarOriginal: String,
    val avatarMax: String,
    val avatarMedium: String,
    val avatarSmall: String,
    val profileUrl: String,
    val hasAvatar: Boolean,
    val isAnonim: Boolean
)
