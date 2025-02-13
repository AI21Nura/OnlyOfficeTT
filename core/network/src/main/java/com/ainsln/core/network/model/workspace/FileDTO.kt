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
    val shared: Boolean,
    val created: String,
    val updated: String,
    val createdBy: UserDTO,
    val updatedBy: UserDTO,
    val webUrl: String
)
