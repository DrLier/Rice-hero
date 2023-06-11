package com.example.capstone.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.data.WeatherPreference
import com.example.capstone.di.InjectWeatherRepo
import com.example.capstone.mainpage.ui.home.HomeViewModel
import com.example.capstone.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope

class HomeFactory private constructor(private val weatherRepository: WeatherRepository) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(weatherRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance : HomeFactory? = null
        fun getInstance(pref: WeatherPreference) : HomeFactory =
            instance ?: synchronized(this) {
                instance ?: HomeFactory(InjectWeatherRepo.provideRepository(pref))
            }.also { instance = it }
    }
}