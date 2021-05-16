package com.example.rebajitasdiego.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.rebajitasdiego.db.entities.EatingDataEntity
import com.example.rebajitasdiego.db.entities.UserProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {

    @Query(
        """
        select * from UserProfileEntity where id = 1
    """
    )
    fun getUser(): Flow<UserProfileEntity?>

    @Insert
    suspend fun insertUserForFirstTime(userProfileEntity: UserProfileEntity)

    @Update
    suspend fun updateUser(userProfileEntity: UserProfileEntity)
}