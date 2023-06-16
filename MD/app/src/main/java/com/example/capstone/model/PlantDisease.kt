package com.example.capstone.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "listPestDisease")
@Parcelize
data class PlantDisease(
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey(autoGenerate = false)
    var id : Int,

    @field:ColumnInfo(name = "name")
    val name : String,

    @field:ColumnInfo(name = "type")
    val type : String,

    @field:ColumnInfo(name = "photo")
    val photo : String,

    @field:ColumnInfo(name = "symptom")
    val symptom : String,

    @field:ColumnInfo(name = "cause")
    val cause : String,

    @field:ColumnInfo(name = "effect")
    val effect : String,

    @field:ColumnInfo(name = "control")
    val control : String
) : Parcelable
