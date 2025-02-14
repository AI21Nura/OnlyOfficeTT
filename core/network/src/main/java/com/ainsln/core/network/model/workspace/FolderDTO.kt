package com.ainsln.core.network.model.workspace

import kotlinx.serialization.Serializable

@Serializable
data class FolderDTO(
    val id: Long,
    val parentId: Long,
    val rootFolderId: Long,
    val title: String,
    val filesCount: Int,
    val foldersCount: Int,
    val mute: Boolean,
    val pinned: Boolean,
    val private: Boolean,
    val canShare: Boolean,
    val shared: Boolean,
    val created: String,
    val updated: String,
    val createdBy: UserDTO,
    val updatedBy: UserDTO,
    val denyDownload: Boolean,
    //Trash fields
    val originId: Int? = null,
    val originTitle: String? = null,
    val autoDelete: String? = null,
    //Room fields
    val tags: List<String>? = null,
    val logo: RoomLogoDTO? = null,
    val roomType: Int? = null,
    val inRoom: Boolean? = null
)
