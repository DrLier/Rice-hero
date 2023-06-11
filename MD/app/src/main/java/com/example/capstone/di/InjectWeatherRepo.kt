package com.example.capstone.di

import android.content.Context
import com.example.capstone.api.ApiConfig
import com.example.capstone.data.WeatherPreference
import com.example.capstone.database.PestDiseaseDatabase
import com.example.capstone.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope

object InjectWeatherRepo {
    fun provideRepository(pref : WeatherPreference) : WeatherRepository {
        val apiService = ApiConfig.getApiService()
        return WeatherRepository.getInstance(apiService, pref)
    }
}