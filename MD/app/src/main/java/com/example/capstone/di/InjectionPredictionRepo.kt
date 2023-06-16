package com.example.capstone.di

import com.example.capstone.repository.PredictionRepository

object InjectionPredictionRepo {
    fun provideRepository() : PredictionRepository {
        return PredictionRepository.getInstance()
    }
}