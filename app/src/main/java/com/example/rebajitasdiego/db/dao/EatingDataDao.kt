package com.example.rebajitasdiego.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.rebajitasdiego.db.entities.EatingDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EatingDataDao {
    @Query(
        """
        select * from EatingDataEntity
    """
    )
    fun getAllEatingData(): Flow<List<EatingDataEntity>>

    @Query(
        """
        select * from EatingDataEntity where id = :id
    """
    )
    fun getEatingDataById(id: Int): Flow<EatingDataEntity?>

    @Query(
        """
        select * from EatingDataEntity where id = :id
    """
    )
    suspend fun getEatingDataByIdSync(id: Int): EatingDataEntity?

    @Insert
    suspend fun insertOne(entity: EatingDataEntity)

    @Insert
    suspend fun insertAll(entities: List<EatingDataEntity>)

    @Update
    suspend fun updateOne(entity: EatingDataEntity)

    @Update
    suspend fun updateAll(entity: List<EatingDataEntity>)

    @Query("""
        update EatingDataEntity set counter = 0, isLastHalf = 0
    """)
    fun clearAllData()
}