package com.example.anmp_project.util

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.anmp_project.model.UserDatabase


val DB_NAME = "esportdb"

fun buildDb(context:Context):UserDatabase {
    val db = UserDatabase.buildDatabase(context)
    return db
}

val MIGRATION_1_2 = object: Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo " +
                "ADD COLUMN priority INTEGER DEFAULT 3 not NULL")
    }
}