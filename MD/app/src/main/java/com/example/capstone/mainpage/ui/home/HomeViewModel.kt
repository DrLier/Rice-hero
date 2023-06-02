package com.example.capstone.mainpage.ui.home

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.capstone.api.WeatherResponse
import com.example.capstone.data.WeatherPreference
import com.example.capstone.model.Weather
import com.example.capstone.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository : HomeRepository) : ViewModel() {

    fun getCurrentWeather(location : Location) = homeRepository.getCurrentWeather(location)

    fun checkCurrentWeather() = homeRepository.currentWeather

    fun saveWeather(weather: Weather) {
        viewModelScope.launch {
            homeRepository.saveWeather(weather)
        }
    }

    fun getWeather() : LiveData<Weather> = homeRepository.getWeather()

}