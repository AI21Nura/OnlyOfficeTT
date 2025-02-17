package com.ainsln.core.data.mapper

import com.ainsln.core.model.storage.FileStorage
import com.ainsln.core.model.storage.document.File
import com.ainsln.core.model.storage.document.Folder
import com.ainsln.core.network.model.storage.FileDTO
import com.ainsln.core.network.model.storage.FolderDTO
import com.ainsln.core.network.model.storage.StorageDTO
import javax.inject.Inject

interface BaseStorageMapper {
    fun dtoToDomain(dto: StorageDTO): FileStorage
    fun dtoToDomain(dto: FileDTO): File
    fun dtoToDomain(dto: FolderDTO): Folder
}

class BaseStorageMapperImpl @Inject constructor(): BaseStorageMapper {

    override fun dtoToDomain(dto: StorageDTO) = FileStorage(
        files = dto.files.map { dtoToDomain(it) },
        folders = dto.folders.map { dtoToDomain(it) },
        current = dtoToDomain(dto.current),
        total = dto.total
    )

    override fun dtoToDomain(dto: FileDTO) = File(
        id = dto.id,
        folderId = dto.folderId,
        contentLength = dto.contentLength,
        title = dto.title
    )

    override fun dtoToDomain(dto: FolderDTO) = Folder(
        id = dto.id,
        title = dto.title,
        filesCount = dto.filesCount,
        foldersCount = dto.foldersCount
    )
}



