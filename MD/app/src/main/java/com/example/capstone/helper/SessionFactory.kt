package com.example.capstone.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.accesslocation.AccessLocationViewModel
import com.example.capstone.data.SessionPreference
import com.example.capstone.mainpage.MainPageViewModel
import com.example.capstone.mainpage.ui.home.HomeViewModel

class SessionFactory(private val pref : SessionPreference) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AccessLocationViewModel::class.java) -> {
                AccessLocationViewModel(pref) as T
            }
            modelClass.isAssignableFrom(MainPageViewModel::class.java) -> {
                MainPageViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}