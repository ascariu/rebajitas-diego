package com.example.rebajitasdiego.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rebajitasdiego.db.dao.EatingDataDao
import com.example.rebajitasdiego.db.dao.UserProfileDao
import com.example.rebajitasdiego.db.entities.EatingDataEntity
import com.example.rebajitasdiego.db.entities.UserProfileEntity


@Database(
    version= 2,
    entities = [EatingDataEntity::class, UserProfileEntity::class]
)

abstract class RebajitasDatabase : RoomDatabase() {

    companion object{
        fun init(context: Context): RebajitasDatabase {
            return Room.databaseBuilder(
                context,
                RebajitasDatabase::class.java,
                "__db__RebajitasDiego"
            )
                .createFromAsset("__db__RebajitasDiego_seeded")
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun eatingDataDao(): EatingDataDao
    abstract fun userProfileDao(): UserProfileDao

}

