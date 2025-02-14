package com.ainsln.core.network.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.ainsln.core.network.utils.SessionInterceptor
import com.ainsln.core.network.utils.token.BaseSessionManager
import com.ainsln.core.network.utils.token.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object UtilsModule {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "com.ainsln.onlyoffice.preferences"
    )

    @Provides
    @Singleton
    fun provideSessionManager(
        @ApplicationContext context: Context
    ): SessionManager {
        return BaseSessionManager(context.dataStore)
    }

    @Provides
    @Singleton
    fun provideSessionInterceptor(
        sessionManager: SessionManager
    ): SessionInterceptor {
        return SessionInterceptor(sessionManager)
    }
}
