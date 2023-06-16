package com.example.capstone.mainpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstone.data.SessionPreference

class MainPageViewModel(private val pref : SessionPreference) : ViewModel() {

    fun getSession() : LiveData<Boolean> {
        return pref.getSession().asLiveData()
    }
}