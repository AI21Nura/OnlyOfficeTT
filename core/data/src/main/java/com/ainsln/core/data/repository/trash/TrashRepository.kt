package com.ainsln.core.data.repository.trash

import com.ainsln.core.common.result.DataResult
import com.ainsln.core.model.storage.TrashStorage
import kotlinx.coroutines.flow.Flow

interface TrashRepository {
    fun getTrash(): Flow<DataResult<TrashStorage>>
}
