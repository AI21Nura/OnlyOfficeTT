package com.ainsln.core.data.repository.room

import com.ainsln.core.common.result.DataResult
import com.ainsln.core.data.mapper.RoomMapper
import com.ainsln.core.data.utils.NetworkExecutor
import com.ainsln.core.model.storage.RoomStorage
import com.ainsln.core.network.NetworkDataSource
import com.ainsln.core.network.utils.token.SessionManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val mapper: RoomMapper,
    private val sessionManager: SessionManager,
    private val networkExecutor: NetworkExecutor
) : RoomRepository {

    override fun getRooms(): Flow<DataResult<RoomStorage>> {
        return networkExecutor.execute(
            networkCall = NetworkDataSource::getRooms,
            mapper = {
                val portal = sessionManager.getPortal(includeScheme = true).first()
                mapper.dtoToDomain(it, portal)
            }
        )
    }
}

