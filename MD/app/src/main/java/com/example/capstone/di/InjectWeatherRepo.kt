package com.example.capstone.di

import com.example.capstone.api.ApiConfig
import com.example.capstone.repository.WeatherRepository

object InjectWeatherRepo {
    fun provideRepository() : WeatherRepository {
        val apiService = ApiConfig.getApiService()
        return WeatherRepository.getInstance(apiService)
    }
}