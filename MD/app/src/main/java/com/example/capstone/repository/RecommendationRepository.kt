package com.example.capstone.repository

import com.example.capstone.database.RecommendationDao
import com.example.capstone.model.DailyRecommendation
import com.example.capstone.model.Planting

class RecommendationRepository(
    private val recommendationDao: RecommendationDao
) {

    fun getPlanting() = recommendationDao.getPlanting()

    suspend fun insertInitiateData(dailyRecommendation: List<DailyRecommendation>) = recommendationDao.insertInitiateRecommendation(dailyRecommendation)

    suspend fun insertPlanting(planting: Planting) = recommendationDao.insertPlanting(planting)

    fun getDailyRecommendation(id : Int) = recommendationDao.getRecommendation(id)

    companion object {
        @Volatile
        private var INSTANCE : RecommendationRepository? = null

        fun getInstance(recommendationDao: RecommendationDao) : RecommendationRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RecommendationRepository(recommendationDao)
            }.also { INSTANCE = it }
    }
}