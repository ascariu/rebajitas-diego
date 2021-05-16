package com.example.rebajitasdiego.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.rebajitasdiego.db.entities.UserProfileEntity
import com.example.rebajitasdiego.models.Gender

class UserProfileMigration(val myDb: RebajitasDatabase): Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "INSERT OR IGNORE INTO UserProfileEntity (id,name, gender) VALUES(1, \"Diego\", \"Male\")"
        )
    }
}