package com.ainsln.core.network.di

import com.ainsln.core.network.NetworkDataSource
import com.ainsln.core.network.retrofit.RetrofitDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkDataSourceModule {

    @Binds
    @Singleton
    fun bindRetrofitDataSource(
        dataSource: RetrofitDataSource
    ) : NetworkDataSource
}
