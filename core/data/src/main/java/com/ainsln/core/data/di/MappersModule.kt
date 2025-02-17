package com.ainsln.core.data.di

import com.ainsln.core.data.mapper.AuthMapper
import com.ainsln.core.data.mapper.AuthMapperImpl
import com.ainsln.core.data.mapper.BaseStorageMapper
import com.ainsln.core.data.mapper.BaseStorageMapperImpl
import com.ainsln.core.data.mapper.ProfileMapper
import com.ainsln.core.data.mapper.ProfileMapperImpl
import com.ainsln.core.data.mapper.RoomMapper
import com.ainsln.core.data.mapper.RoomMapperImpl
import com.ainsln.core.data.mapper.TrashMapper
import com.ainsln.core.data.mapper.TrashMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface MappersModule {
    @Binds
    @Singleton
    fun bindAuthMapper(mapper: AuthMapperImpl): AuthMapper

    @Binds
    @Singleton
    fun bindBaseStorageMapper(
        mapper: BaseStorageMapperImpl
    ): BaseStorageMapper

    @Binds
    @Singleton
    fun bindTrashMapper(
        mapper: TrashMapperImpl
    ): TrashMapper

    @Binds
    @Singleton
    fun bindRoomMapper(
        mapper: RoomMapperImpl
    ): RoomMapper

    @Binds
    @Singleton
    fun bindProfileMapper(
        mapper: ProfileMapperImpl
    ): ProfileMapper
}
