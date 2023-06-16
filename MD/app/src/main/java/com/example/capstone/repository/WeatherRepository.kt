package com.example.capstone.repository

import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstone.api.ApiService
import com.example.capstone.api.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository private constructor(
    private val apiService: ApiService,
){

    private val _fiveDaysWeather = MutableLiveData<WeatherResponse>()
    val fiveDaysWeather : LiveData<WeatherResponse> = _fiveDaysWeather

    fun getFiveDaysWeather(location: Location) {
        val client = apiService.getFiveDaysWeather(location.latitude, location.longitude, "9e3120b9fe7fd347f870ba5d971f7b9c")
        client.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    _fiveDaysWeather.value = response.body()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("TAG123", t.toString())
            }
        })
    }

    companion object {
        @Volatile
        private var instance : WeatherRepository? = null
        fun getInstance(apiService: ApiService) : WeatherRepository =
            instance ?: synchronized(this) {
                instance ?: WeatherRepository(apiService)
            }.also { instance = it }
    }
}