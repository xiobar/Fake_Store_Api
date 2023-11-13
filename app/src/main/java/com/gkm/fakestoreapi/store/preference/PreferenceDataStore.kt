package com.gkm.fakestoreapi.store.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
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
        val nameKey = stringPreferencesKey(NAME_KEY)
    }

    suspend fun saveName(name:String){
        dataStore.edit {preferences->
            preferences[DataStoreKeys.nameKey] = name
        }
    }

    val readName: Flow<String> = dataStore.data.map { preferences ->
        preferences[DataStoreKeys.nameKey]?:""
    }
}