package com.ainsln.core.model.storage.trash

import com.ainsln.core.model.storage.document.Folder
import java.util.Date

data class TrashFolder(
    val base: Folder,
    val originId: Int,
    val originTitle: String,
    val autoDelete: Date,
    val remainingDays: Int
)
