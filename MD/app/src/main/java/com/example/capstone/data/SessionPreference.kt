package com.example.capstone.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class SessionPreference private constructor(private val dataStore: DataStore<Preferences>){



    fun getSession() : Flow<Boolean> {
        return dataStore.data.map {
            it[session] ?: false
        }
    }

    suspend fun saveSession(isSession : Boolean) {
        dataStore.edit {
            it[session] = isSession
        }
    }

    companion object {
        @Volatile
        private var INSTANCE : SessionPreference? = null

        private val session = booleanPreferencesKey("session")

        fun getInstance(dataStore: DataStore<Preferences>) : SessionPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = SessionPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}