package com.ainsln.core.data.repository.document

import com.ainsln.core.common.result.DataResult
import com.ainsln.core.model.storage.FileStorage
import kotlinx.coroutines.flow.Flow

interface DocumentRepository {
    fun getMyDocuments(folderId: Long?): Flow<DataResult<FileStorage>>
}

