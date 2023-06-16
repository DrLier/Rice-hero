package com.example.capstone.di

import android.content.Context
import com.example.capstone.database.RecommendationDatabase
import com.example.capstone.repository.RecommendationRepository

object InjectionRecommendationRepo {
    fun provideRepository(context: Context) : RecommendationRepository {
        val database = RecommendationDatabase.getDatabase(context)
        val recommendationDao = database.recommendationDao()
        return RecommendationRepository.getInstance(recommendationDao)
    }
}