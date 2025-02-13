package com.ainsln.core.network.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthBody(
    val userName: String,
    val password: String
)
