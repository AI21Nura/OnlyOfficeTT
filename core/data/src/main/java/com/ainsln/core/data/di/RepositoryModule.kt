package com.ainsln.core.data.di

import com.ainsln.core.data.repository.auth.AuthRepository
import com.ainsln.core.data.repository.auth.AuthRepositoryImpl
import com.ainsln.core.data.repository.document.DocumentRepository
import com.ainsln.core.data.repository.document.DocumentRepositoryImpl
import com.ainsln.core.data.repository.profile.ProfileRepository
import com.ainsln.core.data.repository.profile.ProfileRepositoryImpl
import com.ainsln.core.data.repository.room.RoomRepository
import com.ainsln.core.data.repository.room.RoomRepositoryImpl
import com.ainsln.core.data.repository.trash.TrashRepository
import com.ainsln.core.data.repository.trash.TrashRepositoryImpl
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

    @Binds
    @Singleton
    fun MyDocumentsRepository(repo: DocumentRepositoryImpl): DocumentRepository

    @Binds
    @Singleton
    fun TrashRepository(repo: TrashRepositoryImpl): TrashRepository

    @Binds
    @Singleton
    fun RoomRepository(repo: RoomRepositoryImpl): RoomRepository

    @Binds
    @Singleton
    fun ProfileRepository(repo: ProfileRepositoryImpl): ProfileRepository
}
