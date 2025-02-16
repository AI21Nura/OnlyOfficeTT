package com.ainsln.core.model.storage

import com.ainsln.core.model.storage.document.File
import com.ainsln.core.model.storage.document.Folder
import com.ainsln.core.model.storage.room.Room
import com.ainsln.core.model.storage.trash.TrashFile
import com.ainsln.core.model.storage.trash.TrashFolder

data class Storage<T, R>(
    val files: List<T>,
    val folders: List<R>,
    val current: R,
    val total: Int
)

data class RoomStorage(
    val rooms: List<Room>,
    val current: Folder,
    val total: Int
)

typealias FileStorage = Storage<File, Folder>
typealias TrashStorage = Storage<TrashFile, TrashFolder>
