package com.ainsln.core.network.model.workspace

import kotlinx.serialization.Serializable

/**
 * Removed fields from original JSON:
 * - Detailed security settings
 * - Various accessibility settings
 * - External rights information
 * - Thumbnail status
 * - File entry types
 * - Mute/pinned/private statuses
 * - Various URLs except main web URL
 * - Indexing information
 * - Root folder information
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
