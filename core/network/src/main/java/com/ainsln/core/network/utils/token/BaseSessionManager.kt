package com.ainsln.core.network.utils.token

import android.service.notification.Condition.SCHEME
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ainsln.core.network.utils.token.SessionManager.Companion.BASE_SCHEME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class BaseSessionManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SessionManager {

    override fun isAuthorized(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[KEY_TOKEN] != null
                    && preferences[KEY_PORTAL] != null
        }
    }

    override suspend fun saveAuthData(portal: String, token: String) {
        dataStore.edit { preferences ->
            preferences[KEY_TOKEN] = token
            preferences[KEY_PORTAL] = portal.removePrefix(BASE_SCHEME)
        }
    }

    override suspend fun deleteAuthData() {
        dataStore.edit { preferences ->
            preferences.remove(KEY_TOKEN)
            preferences.remove(KEY_PORTAL)
        }
    }
    override fun getToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[KEY_TOKEN] ?: ""
        }
    }

    override fun getPortal(includeScheme: Boolean): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[KEY_PORTAL]?.let { portal ->
                if (includeScheme) SCHEME + portal
                else portal
            } ?: ""
        }
    }

    companion object {
        private val KEY_TOKEN = stringPreferencesKey("token")
        private val KEY_PORTAL = stringPreferencesKey("portal")
    }
}
