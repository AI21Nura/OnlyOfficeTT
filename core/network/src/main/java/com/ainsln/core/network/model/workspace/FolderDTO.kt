package com.ainsln.core.network.model.workspace

import kotlinx.serialization.Serializable

@Serializable
data class FolderDTO(
    val id: Long,
    val parentId: Long,
    val title: String,
    val filesCount: Int,
    val foldersCount: Int,
    val mute: Boolean,
    val pinned: Boolean,
    val private: Boolean,
    val shared: Boolean,
    val created: String,
    val updated: String,
    val createdBy: UserDTO,
    val updatedBy: UserDTO
)
