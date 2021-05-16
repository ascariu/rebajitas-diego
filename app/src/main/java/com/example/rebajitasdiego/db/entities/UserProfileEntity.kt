package com.example.rebajitasdiego.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rebajitasdiego.models.Gender

@Entity()
data class UserProfileEntity (
    @PrimaryKey()
    val id: Int = 1,
    val name: String = "Diego",
    val gender: Gender = Gender.Male
)