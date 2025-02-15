package com.ainsln.core.network.model.storage

import kotlinx.serialization.Serializable

@Serializable
data class RoomLogoDTO(
    val original: String,
    val large: String,
    val medium: String,
    val small: String,
    val color: String
)
