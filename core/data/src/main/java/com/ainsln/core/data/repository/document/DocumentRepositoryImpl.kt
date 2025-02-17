package com.ainsln.core.data.repository.document

import com.ainsln.core.common.result.DataResult
import com.ainsln.core.data.mapper.BaseStorageMapper
import com.ainsln.core.data.utils.NetworkExecutor
import com.ainsln.core.model.storage.FileStorage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DocumentRepositoryImpl @Inject constructor(
    private val mapper: BaseStorageMapper,
    private val networkExecutor: NetworkExecutor
) : DocumentRepository {

    override fun getMyDocuments(folderId: Long?): Flow<DataResult<FileStorage>> {
        return networkExecutor.execute(
            networkCall = {
                if (folderId != null) getFolderById(folderId)
                else getMyDocuments()
            },
            mapper = mapper::dtoToDomain
        )
    }
}
