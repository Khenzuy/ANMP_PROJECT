package com.example.anmp_project.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Schedule::class], version = 2, exportSchema = false)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: ScheduleDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ScheduleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScheduleDatabase::class.java,
                    "schedule_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        fun loadJsonFromAsset(fileName: String, context: Context): String {
            return context.assets.open(fileName).bufferedReader().use {
                it.readText()
            }
        }

        suspend fun populateDatabase(context: Context): List<Schedule> {
            val jsonString = loadJsonFromAsset("data.json", context)
            val gson = Gson()
            val typeToken = object : TypeToken<List<Schedule>>() {}.type
            return gson.fromJson(jsonString, typeToken)
        }
    }
}

