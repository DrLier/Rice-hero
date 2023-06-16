package com.example.capstone.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capstone.model.DailyRecommendation
import com.example.capstone.model.Planting

@Database(entities = [Planting::class , DailyRecommendation::class], version = 2, exportSchema = false)
abstract class RecommendationDatabase : RoomDatabase(){
    abstract fun recommendationDao() : RecommendationDao

    companion object {
        @Volatile
        private var INSTANCE : RecommendationDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context) : RecommendationDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    RecommendationDatabase::class.java, "recommendation_database"
                ).fallbackToDestructiveMigration().createFromAsset("recommendation_database.db").build()
            }
    }
}