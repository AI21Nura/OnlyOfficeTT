package com.ainsln.core.network.model.storage

import kotlinx.serialization.Serializable

/**
 * Ignored fields from original JSON:
 * - Version
 * - Detailed security settings
 * - Various accessibility settings
 * - External rights information
 * - Thumbnail status, Comment
 * - Pure length
 * - Various URLs except main web URL
 * - Indexing information
 */
@Serializable
data class StorageDTO(
    val files: List<FileDTO>,
    val folders: List<FolderDTO>,
    val current: FolderDTO,
    val startIndex: Int,
    val count: Int,
    val total: Int,
    val new: Int
)
