package com.example.capstone.mainpage.ui.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.InitialDataControlling.listDataControlling
import com.example.capstone.model.Planting
import com.example.capstone.repository.RecommendationRepository
import kotlinx.coroutines.launch

class ScheduleViewModel(private val recommendationRepository: RecommendationRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            recommendationRepository.insertInitiateData(listDataControlling)
        }
    }

    fun getPlanting() = recommendationRepository.getPlanting()

    fun getDailyRecommendation(id : Int) = recommendationRepository.getDailyRecommendation(id)

    fun insertPlanting(planting: Planting) {
        viewModelScope.launch {
            recommendationRepository.insertPlanting(planting)
        }
    }
}