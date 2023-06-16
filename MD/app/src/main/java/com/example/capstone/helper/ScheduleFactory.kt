package com.example.capstone.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.di.InjectionRecommendationRepo
import com.example.capstone.mainpage.ui.schedule.ScheduleViewModel
import com.example.capstone.repository.RecommendationRepository

class ScheduleFactory private constructor(private val recommendationRepository: RecommendationRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleViewModel::class.java)) {
            return ScheduleViewModel(recommendationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE : ScheduleFactory? = null
        fun getInstance(context: Context) : ScheduleFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ScheduleFactory(InjectionRecommendationRepo.provideRepository(context))
            }.also { INSTANCE = it }
    }
}