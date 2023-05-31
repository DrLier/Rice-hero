package com.example.capstone.accesslocation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.SessionPreference
import kotlinx.coroutines.launch

class AccessLocationViewModel(private val pref : SessionPreference) : ViewModel() {

    fun saveSession(isSession : Boolean) {
        viewModelScope.launch {
            pref.saveSession(isSession)
        }
    }
}