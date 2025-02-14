package com.ainsln.core.network.model.workspace

import kotlinx.serialization.Serializable

/**
 * Ignored fields from original JSON:
 * - Version
 * - Detailed security settings
 * - Various accessibility settings
 * - External rights information
 * - Thumbnail status, Comment
 * - File entry types & Pure length
 * - Various URLs except main web URL
 * - Indexing information
 */
@Serializable
data class WorkspaceDTO(
    val files: List<FileDTO> = emptyList(),
    val folders: List<FolderDTO> = emptyList(),
    val current: FolderDTO? = null,
    val startIndex: Int,
    val count: Int,
    val total: Int,
    val new: Int
)
