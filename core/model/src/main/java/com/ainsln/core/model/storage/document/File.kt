package com.ainsln.core.model.storage.document

data class File(
    val id: Long,
    val folderId: Long,
    val contentLength: String,
    val title: String
)
