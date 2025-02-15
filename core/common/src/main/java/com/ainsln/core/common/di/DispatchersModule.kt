package com.ainsln.core.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
internal object DispatchersModule {

    @Provides
    @IODispatcher
    fun provideIODispatcher() = Dispatchers.IO

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher() = Dispatchers.Default

    @Provides
    @MainDispatcher
    fun provideMainDispatcher() = Dispatchers.Main

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier annotation class IODispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier annotation class DefaultDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier annotation class MainDispatcher
