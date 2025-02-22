package com.ainsln.core.data.di

import com.ainsln.core.data.utils.BaseDateFormatter
import com.ainsln.core.data.utils.BaseNetworkExecutor
import com.ainsln.core.data.utils.DateFormatter
import com.ainsln.core.data.utils.NetworkExecutor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface UtilsModule {
    @Binds
    @Singleton
    fun bindDateFormatter(
        formatter: BaseDateFormatter
    ): DateFormatter

    @Binds
    @Singleton
    fun bindNetworkExecutor(
        executor: BaseNetworkExecutor
    ): NetworkExecutor
}
