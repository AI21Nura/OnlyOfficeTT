package com.ainsln.core.data.di

import com.ainsln.core.data.repository.auth.AuthRepository
import com.ainsln.core.data.repository.auth.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun AuthRepository(repo: AuthRepositoryImpl): AuthRepository
}
