package com.ainsln.core.model.storage.trash

import com.ainsln.core.model.storage.document.File
import java.util.Date

data class TrashFile(
    val base: File,
    val originId: Int,
    val originTitle: String,
    val autoDelete: Date,
    val remainingDays: Int
)
