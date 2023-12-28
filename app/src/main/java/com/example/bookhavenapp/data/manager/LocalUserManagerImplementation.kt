package com.example.bookhavenapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.bookhavenapp.domain.manager.LocalUserManager
import com.example.bookhavenapp.utils.Constants.APP_ENTRY
import com.example.bookhavenapp.utils.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImplementation(
    private val context: Context
): LocalUserManager {
    override suspend fun saveAppEntry() {
        context.dataStore.edit {
                settings ->
            settings[PreferencesKeys.appEntry] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map {
                preferences ->
            preferences[PreferencesKeys.appEntry] ?: false
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKeys {
    val appEntry = booleanPreferencesKey(name = APP_ENTRY)
}
