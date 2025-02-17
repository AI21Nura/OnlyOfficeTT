package com.ainsln.core.data.repository.trash

import com.ainsln.core.common.result.DataResult
import com.ainsln.core.data.mapper.TrashMapper
import com.ainsln.core.data.utils.NetworkExecutor
import com.ainsln.core.model.storage.TrashStorage
import com.ainsln.core.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class TrashRepositoryImpl @Inject constructor(
    private val mapper: TrashMapper,
    private val networkExecutor: NetworkExecutor
) : TrashRepository {

    override fun getTrash(): Flow<DataResult<TrashStorage>> {
        return networkExecutor.execute(
            networkCall = NetworkDataSource::getTrash,
            mapper = { mapper.dtoToDomain(it) }
        )
    }
}
