package com.example.capstone.mainpage.ui.home

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.model.Weather
import com.example.capstone.repository.WeatherRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val weatherRepository : WeatherRepository) : ViewModel() {

    fun getCurrentWeather(location : Location) = weatherRepository.getCurrentWeather(location)

    fun checkCurrentWeather() = weatherRepository.currentWeather

    fun saveWeather(weather: Weather) {
        viewModelScope.launch {
            weatherRepository.saveWeather(weather)
        }
    }

    fun getWeather() : LiveData<Weather> = weatherRepository.getWeather()

}