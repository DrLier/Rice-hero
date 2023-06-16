package com.example.capstone.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_recommendation")
data class DailyRecommendation (
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey(autoGenerate = false)
    val id : Int,

    @field:ColumnInfo(name = "recommendation")
    val recommendation : String
)