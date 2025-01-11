package com.example.anmp_project.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.anmp_project.model.Achievement
import com.example.anmp_project.model.AchievementDao
import com.example.anmp_project.model.Competition
import com.example.anmp_project.model.CompetitionDao
import com.example.anmp_project.model.CompetitionJson
import com.example.anmp_project.model.User
import com.example.anmp_project.model.UserDao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

@Database(entities = [User::class, Competition::class, Achievement::class], version = 1)
abstract class EsportsDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun competitionDao(): CompetitionDao
    abstract fun achievementDao(): AchievementDao

    companion object {
        @Volatile
        private var INSTANCE: EsportsDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): EsportsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EsportsDatabase::class.java,
                    "esports_db"
                ).addCallback(DatabaseCallback(scope, context))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(
            private val scope: CoroutineScope,
            private val context: Context
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) { // Ensure this runs on a background thread
                        populateDatabase(database, context)
                    }
                }
            }
        }

        suspend fun populateDatabase(database: EsportsDatabase, context: Context) {
            val jsonString = loadJsonFromAsset("data.json", context)
            val gson = Gson()
            val typeToken = object : TypeToken<CompetitionsWrapper>() {}.type
            val competitionsWrapper: CompetitionsWrapper = gson.fromJson(jsonString, typeToken)

            competitionsWrapper.competitions.forEach { comp ->
                val competition = Competition(
                    id = comp.id,
                    gamePhoto = comp.gamePhoto,
                    gameName = comp.gameName,
                    gameShortName = comp.gameShortName,
                    gameDescription = comp.gameDescription
                )
                database.competitionDao().insertCompetition(competition)

                comp.teamAchievements.forEach { achievement ->
                    val newAchievement = Achievement(
                        achievement = achievement.achievement,
                        teamName = achievement.teamName,
                        year = achievement.year,
                        competitionId = comp.id
                    )
                    database.achievementDao().insertAchievement(newAchievement)
                }
            }
        }


        private fun loadJsonFromAsset(filename: String, context: Context): String? {
            return try {
                context.assets.open(filename).bufferedReader().use { it.readText() }
            } catch (ex: IOException) {
                ex.printStackTrace()
                null
            }
        }
    }
}
