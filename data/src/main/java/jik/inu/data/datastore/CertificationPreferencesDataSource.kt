package jik.inu.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class CertificationPreferencesDataSource @Inject constructor(
    @Named("certification") private val certificationPreferences: DataStore<Preferences>
) {
    object Key {
        val ACCESS_TOKEN = stringPreferencesKey("ACCESS_TOKEN")
    }

    val accessToken: Flow<String> =
        certificationPreferences.data.map { preferences ->
            preferences[Key.ACCESS_TOKEN] ?: ""
        }

    suspend fun setAccessToken(accessToken: String) {
        certificationPreferences.edit { preferences ->
            preferences[Key.ACCESS_TOKEN] = accessToken
        }
    }

    suspend fun deleteAccessToken() {
        certificationPreferences.edit { preferences ->
            preferences.remove(Key.ACCESS_TOKEN)
        }
    }
}