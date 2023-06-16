package com.example.capstone.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.capstone.model.DailyRecommendation
import com.example.capstone.model.Planting

@Dao
interface RecommendationDao {
    @Query("SELECT * FROM daily_recommendation")
    fun getListRecommendation() : LiveData<List<DailyRecommendation>>

    @Query("SELECT * FROM daily_recommendation WHERE id = :id")
    fun getRecommendation(id : Int) : LiveData<DailyRecommendation>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertInitiateRecommendation(dailyRecommendation: List<DailyRecommendation>)

    @Query("DELETE FROM daily_recommendation")
    suspend fun deleteAllRecommendation()

    @Query("SELECT * FROM planting")
    fun getPlanting() : LiveData<Planting>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlanting(planting: Planting)

    @Query("DELETE FROM planting")
    suspend fun deleteAllPlanting()
}