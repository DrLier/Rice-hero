package com.example.capstone.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.capstone.model.Weather
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

class WeatherPreference private constructor(private val dataStore: DataStore<Preferences>){

    fun getWeather() : Flow<Weather> {
        return dataStore.data.map {
            Weather(
                it[LOCATION_KEY] ?: "",
                it[TEMPERATURE_KEY] ?: 0,
                it[DESCRIPTION_KEY] ?: ""
            )
        }
    }

    suspend fun saveWeather(weather: Weather) {
        dataStore.edit {
            it[LOCATION_KEY] = weather.location
            it[TEMPERATURE_KEY] = weather.temperature
            it[DESCRIPTION_KEY] = weather.description
        }
    }

    companion object {
        @Volatile
        private var INSTANCE : WeatherPreference ? = null

        private val LOCATION_KEY = stringPreferencesKey("location")
        private val TEMPERATURE_KEY = intPreferencesKey("temperature")
        private val DESCRIPTION_KEY = stringPreferencesKey("description")

        fun getInstance(dataStore: DataStore<Preferences>) : WeatherPreference {
            return INSTANCE?: synchronized(this) {
                val instance = WeatherPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}