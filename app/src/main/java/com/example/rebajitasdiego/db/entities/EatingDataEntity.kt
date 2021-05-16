package com.example.rebajitasdiego.db.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.rebajitasdiego.models.FoodCategory

@Entity(indices = [Index("category", unique= true)])
data class EatingDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val counter: Int = 0,
    val category: FoodCategory,
    val isLastHalf: Boolean
)