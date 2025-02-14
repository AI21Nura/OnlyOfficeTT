package com.ainsln.core.network.model.workspace

import kotlinx.serialization.Serializable

@Serializable
data class FileDTO(
    val id: Long,
    val folderId: Long,
    val rootFolderId: Long,
    val contentLength: String,
    val fileExst: String,
    val title: String,
    val canShare: Boolean,
    val mute: Boolean,
    val shared: Boolean,
    val created: String,
    val updated: String,
    val createdBy: UserDTO,
    val updatedBy: UserDTO,
    val webUrl: String,
    val originId: Int? = null,
    val originTitle: String? = null,
    val autoDelete: String? = null
)
