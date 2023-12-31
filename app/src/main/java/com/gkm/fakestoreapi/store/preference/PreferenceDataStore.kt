package com.gkm.fakestoreapi.store.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class PreferenceDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private object DataStoreKeys{
        const val NAME_KEY = "NAME_KEY"
        const val PASS_KEY = "PASS_KEY"
        const val SAVE_SWITCH = "SAVE_SWITCH"
        val nameKey = stringPreferencesKey(NAME_KEY)
        val passKey = stringPreferencesKey(PASS_KEY)
        val saveSwitch = booleanPreferencesKey(SAVE_SWITCH)
    }

    suspend fun saveCredentials(name:String, password:String, saveSwitch:Boolean){
        dataStore.edit {preferences->
            preferences[DataStoreKeys.nameKey] = name
            preferences[DataStoreKeys.passKey] = password
            preferences[DataStoreKeys.saveSwitch] = saveSwitch
        }
    }

    val readCredentials: Flow<UserCredentials> = dataStore.data.map { preferences->
        UserCredentials(
            name = preferences[DataStoreKeys.nameKey] ?: "",
            pass = preferences[DataStoreKeys.passKey] ?: "",
            saveSwitch = preferences[DataStoreKeys.saveSwitch] ?: false
        )
    }
}