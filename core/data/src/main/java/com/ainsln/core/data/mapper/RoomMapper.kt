package com.ainsln.core.data.mapper

import com.ainsln.core.model.storage.RoomStorage
import com.ainsln.core.model.storage.room.Room
import com.ainsln.core.model.storage.room.RoomLogo
import com.ainsln.core.network.model.storage.FolderDTO
import com.ainsln.core.network.model.storage.StorageDTO
import javax.inject.Inject

interface RoomMapper {
    fun dtoToDomain(dto: StorageDTO, portal: String): RoomStorage
    fun dtoToDomain(dto: FolderDTO, portal: String): Room
}

internal class RoomMapperImpl @Inject constructor(
    private val baseMapper: BaseStorageMapper
) : RoomMapper {
    override fun dtoToDomain(dto: StorageDTO, portal: String) = RoomStorage(
        rooms = dto.folders.map { dtoToDomain(it, portal) },
        current = baseMapper.dtoToDomain(dto.current),
        total = dto.total

    )

    override fun dtoToDomain(dto: FolderDTO, portal: String) = Room(
        base = baseMapper.dtoToDomain(dto),
        logo = RoomLogo(
            original = dto.logo?.original?.let { url -> portal + url },
            color = dto.logo?.color?.let { "#$it" }
        )
    )
}
