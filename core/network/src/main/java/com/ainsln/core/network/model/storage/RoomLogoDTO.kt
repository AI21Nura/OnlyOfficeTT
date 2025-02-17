package com.ainsln.core.network.model.storage

import kotlinx.serialization.Serializable

@Serializable
data class RoomLogoDTO(
    val original: String? = null,
    val large: String? = null,
    val medium: String? = null,
    val small: String? = null,
    val color: String? = null
)
