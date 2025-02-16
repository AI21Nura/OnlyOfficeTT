package com.ainsln.core.model.profile

data class UserProfile(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val avatarOriginalUrl: String
)
