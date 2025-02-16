package com.ainsln.core.data.mapper

import com.ainsln.core.model.profile.UserProfile
import com.ainsln.core.network.model.profile.UserProfileDTO
import javax.inject.Inject

interface ProfileMapper {
    fun dtoToDomain(dto: UserProfileDTO, portal: String): UserProfile
}

internal class ProfileMapperImpl @Inject constructor() : ProfileMapper {
    override fun dtoToDomain(dto: UserProfileDTO, portal: String) = UserProfile(
        id = dto.id,
        firstName = dto.firstName,
        lastName = dto.lastName,
        email = dto.email,
        avatarOriginalUrl = portal + dto.avatarOriginal
    )
}
