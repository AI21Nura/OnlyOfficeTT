package com.ainsln.core.common.di

import com.ainsln.core.common.exception.BaseExceptionHandler
import com.ainsln.core.common.exception.ExceptionHandler
import com.ainsln.core.common.utils.AndroidLogcatLogger
import com.ainsln.core.common.utils.Logger
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
    fun bindLogger(logger: AndroidLogcatLogger): Logger

    @Binds
    @Singleton
    fun bindExceptionHandler(
        handler: BaseExceptionHandler
    ): ExceptionHandler

}
