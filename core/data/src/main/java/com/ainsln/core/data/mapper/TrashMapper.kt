package com.ainsln.core.data.mapper

import com.ainsln.core.data.utils.DateFormatter
import com.ainsln.core.model.storage.trash.TrashFile
import com.ainsln.core.model.storage.trash.TrashFolder
import com.ainsln.core.model.storage.TrashStorage
import com.ainsln.core.network.model.storage.FileDTO
import com.ainsln.core.network.model.storage.FolderDTO
import com.ainsln.core.network.model.storage.StorageDTO
import java.util.Date
import javax.inject.Inject

interface TrashMapper {
    fun dtoToDomain(dto: StorageDTO): TrashStorage
    fun dtoToDomain(dto: FileDTO): TrashFile
    fun dtoToDomain(dto: FolderDTO): TrashFolder
}

class TrashMapperImpl @Inject constructor(
    private val baseMapper: BaseStorageMapper,
    private val dateFormatter: DateFormatter
): TrashMapper {

    override fun dtoToDomain(dto: StorageDTO) = TrashStorage(
        files = dto.files.map { dtoToDomain(it) },
        folders = dto.folders.map { dtoToDomain(it) },
        current =  dtoToDomain(dto.current),
        total = dto.total
    )

    override fun dtoToDomain(dto: FileDTO) = TrashFile(
        base = baseMapper.dtoToDomain(dto),
        originId = dto.originId ?: 0,
        originTitle = dto.originTitle ?: "",
        autoDelete = dto.autoDelete?.let { dateFormatter.parseDate(it) } ?: Date(),
        remainingDays = dateFormatter.getRemainingDays(dto.autoDelete)
    )

    override fun dtoToDomain(dto: FolderDTO) = TrashFolder(
        base = baseMapper.dtoToDomain(dto),
        originId = dto.originId ?: 0,
        originTitle = dto.originTitle ?: "",
        autoDelete = dto.autoDelete?.let { dateFormatter.parseDate(it) } ?: Date(),
        remainingDays = dateFormatter.getRemainingDays(dto.autoDelete)
    )
}


