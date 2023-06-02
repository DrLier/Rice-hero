package com.example.capstone.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.data.WeatherPreference
import com.example.capstone.di.Injection
import com.example.capstone.mainpage.ui.home.HomeViewModel
import com.example.capstone.repository.HomeRepository

class HomeFactory private constructor(private val homeRepository: HomeRepository) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(homeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance : HomeFactory? = null
        fun getInstance(pref: WeatherPreference) : HomeFactory =
            instance ?: synchronized(this) {
                instance ?: HomeFactory(Injection.provideRepository(pref))
            }.also { instance = it }
    }
}