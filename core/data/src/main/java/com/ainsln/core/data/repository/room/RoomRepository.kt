package com.ainsln.core.data.repository.room

import com.ainsln.core.common.result.DataResult
import com.ainsln.core.model.storage.RoomStorage
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    fun getRooms(): Flow<DataResult<RoomStorage>>
}
