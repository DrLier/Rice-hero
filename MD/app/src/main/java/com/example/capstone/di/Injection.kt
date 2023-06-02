package com.example.capstone.di

import com.example.capstone.api.ApiConfig
import com.example.capstone.data.WeatherPreference
import com.example.capstone.repository.HomeRepository

object Injection {
    fun provideRepository(pref : WeatherPreference) : HomeRepository {
        val apiService = ApiConfig.getApiService()
        return HomeRepository.getInstance(apiService, pref)
    }
}