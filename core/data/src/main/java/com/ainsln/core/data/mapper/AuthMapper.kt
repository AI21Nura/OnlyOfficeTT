package com.ainsln.core.data.mapper

import com.ainsln.core.model.auth.AuthData
import com.ainsln.core.network.model.auth.AuthBody
import javax.inject.Inject

interface AuthMapper {
    fun domainToDto(domain: AuthData): AuthBody
}

internal class AuthMapperImpl @Inject constructor() : AuthMapper {
    override fun domainToDto(domain: AuthData) =
        AuthBody(
            userName = domain.username,
            password = domain.password
        )
}
