package com.ainsln.core.network.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseData(
    val token: String,
    val expires: String
)

