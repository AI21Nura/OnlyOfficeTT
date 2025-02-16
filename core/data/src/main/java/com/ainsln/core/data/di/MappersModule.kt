package com.ainsln.core.data.di

import com.ainsln.core.data.mapper.AuthMapper
import com.ainsln.core.data.mapper.AuthMapperImpl
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
}
