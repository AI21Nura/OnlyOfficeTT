package com.ainsln.core.data.di

import com.ainsln.core.data.mapper.AuthMapper
import com.ainsln.core.data.mapper.AuthMapperImpl
import com.ainsln.core.data.mapper.ProfileMapper
import com.ainsln.core.data.mapper.ProfileMapperImpl
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
    fun bindProfileMapper(
        mapper: ProfileMapperImpl
    ): ProfileMapper
}
