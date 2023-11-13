package com.gkm.fakestoreapi.store.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore:DataStore<Preferences>  by preferencesDataStore(name = "LOGIN_PREFERENCE")
class PreferenceManager @Inject constructor(@ApplicationContext private val context: Context) {

    companion object{
        private val USER_KEY = stringPreferencesKey("user")
        private val PASS_KEY = stringPreferencesKey("password")
    }

    private val dataStore = context.dataStore

    suspend fun saveUserAndPassword(user:String, password:String){
        dataStore.edit { preference->
            preference[USER_KEY] = user
            preference[PASS_KEY] = password
        }
    }

    val userAndPassword: Flow<UserAndPassword?> = dataStore.data.map { preferences->
        val user = preferences[USER_KEY]
        val password = preferences[PASS_KEY]
        UserAndPassword(user, password)
    }
}