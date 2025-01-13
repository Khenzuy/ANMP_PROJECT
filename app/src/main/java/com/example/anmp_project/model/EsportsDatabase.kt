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

@Database(entities = [User::class, Competition::class, Achievement::class, Proposal::class, Team::class, Member::class], version = 1)
abstract class EsportsDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun competitionDao(): CompetitionDao
    abstract fun achievementDao(): AchievementDao
    abstract fun proposalDao(): ProposalDao
    abstract fun teamDao(): TeamDao
    abstract fun memberDao(): MemberDao

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
                    scope.launch(Dispatchers.IO) {
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

                val teamsForCompetitions = listOf(
                    Team(teamName = "Valorant Team A", competitionId = 1),
                    Team(teamName = "Valorant Team B", competitionId = 1),
                    Team(teamName = "Valorant Team C", competitionId = 1),
                    Team(teamName = "Mobile Legends Team A", competitionId = 2),
                    Team(teamName = "Mobile Legends Team B", competitionId = 2),
                    Team(teamName = "Mobile Legends Team C", competitionId = 2),
                    Team(teamName = "League of Legends Team A", competitionId = 3),
                    Team(teamName = "League of Legends Team B", competitionId = 3),
                    Team(teamName = "League of Legends Team C", competitionId = 3),
                    Team(teamName = "Call of Duty Team A", competitionId = 4),
                    Team(teamName = "Call of Duty Team B", competitionId = 4),
                    Team(teamName = "Call of Duty Team C", competitionId = 4),
                    Team(teamName = "DOTA 2 Team A", competitionId = 5),
                    Team(teamName = "DOTA 2 Team B", competitionId = 5),
                    Team(teamName = "DOTA 2 Team C", competitionId = 5),
                    Team(teamName = "Fortnite Team A", competitionId = 6),
                    Team(teamName = "Fortnite Team B", competitionId = 6),
                    Team(teamName = "Fortnite Team C", competitionId = 6)
                )

                database.teamDao().insertTeams(teamsForCompetitions)

                val membersList = listOf(
                    Member(teamName = "Valorant Team A", memberName = "Valorant A 1", memberRole = "Valorant Role 1", memberImage = "path_to_image_1"),
                    Member(teamName = "Valorant Team A", memberName = "Valorant A 2", memberRole = "Valorant Role 2", memberImage = "path_to_image_2"),
                    Member(teamName = "Valorant Team A", memberName = "Valorant A 3", memberRole = "Valorant Role 3", memberImage = "path_to_image_1"),
                    Member(teamName = "Valorant Team A", memberName = "Valorant A 4", memberRole = "Valorant Role 4", memberImage = "path_to_image_2"),
                    Member(teamName = "Valorant Team A", memberName = "Valorant A 5", memberRole = "Valorant Role 5", memberImage = "path_to_image_1"),
                    Member(teamName = "Valorant Team B", memberName = "Valorant B 1", memberRole = "Valorant Role 1", memberImage = "path_to_image_2"),
                    Member(teamName = "Valorant Team B", memberName = "Valorant B 2", memberRole = "Valorant Role 2", memberImage = "path_to_image_1"),
                    Member(teamName = "Valorant Team B", memberName = "Valorant B 3", memberRole = "Valorant Role 3", memberImage = "path_to_image_2"),
                    Member(teamName = "Valorant Team B", memberName = "Valorant B 4", memberRole = "Valorant Role 4", memberImage = "path_to_image_1"),
                    Member(teamName = "Valorant Team B", memberName = "Valorant B 5", memberRole = "Valorant Role 5", memberImage = "path_to_image_2"),
                    Member(teamName = "Valorant Team C", memberName = "Valorant C 1", memberRole = "Valorant Role 1", memberImage = "path_to_image_2"),
                    Member(teamName = "Valorant Team C", memberName = "Valorant C 2", memberRole = "Valorant Role 2", memberImage = "path_to_image_1"),
                    Member(teamName = "Valorant Team C", memberName = "Valorant C 3", memberRole = "Valorant Role 3", memberImage = "path_to_image_2"),
                    Member(teamName = "Valorant Team C", memberName = "Valorant C 4", memberRole = "Valorant Role 4", memberImage = "path_to_image_1"),
                    Member(teamName = "Valorant Team C", memberName = "Valorant C 5", memberRole = "Valorant Role 5", memberImage = "path_to_image_2"),

                    Member(teamName = "Mobile Legends Team A", memberName = "Mobile Legends A 1", memberRole = "Mobile Legends Role 1", memberImage = "path_to_image_1"),
                    Member(teamName = "Mobile Legends Team A", memberName = "Mobile Legends A 2", memberRole = "Mobile Legends Role 2", memberImage = "path_to_image_2"),
                    Member(teamName = "Mobile Legends Team A", memberName = "Mobile Legends A 3", memberRole = "Mobile Legends Role 3", memberImage = "path_to_image_1"),
                    Member(teamName = "Mobile Legends Team A", memberName = "Mobile Legends A 4", memberRole = "Mobile Legends Role 4", memberImage = "path_to_image_2"),
                    Member(teamName = "Mobile Legends Team A", memberName = "Mobile Legends A 5", memberRole = "Mobile Legends Role 5", memberImage = "path_to_image_1"),
                    Member(teamName = "Mobile Legends Team B", memberName = "Mobile Legends B 1", memberRole = "Mobile Legends Role 1", memberImage = "path_to_image_2"),
                    Member(teamName = "Mobile Legends Team B", memberName = "Mobile Legends B 2", memberRole = "Mobile Legends Role 2", memberImage = "path_to_image_1"),
                    Member(teamName = "Mobile Legends Team B", memberName = "Mobile Legends B 3", memberRole = "Mobile Legends Role 3", memberImage = "path_to_image_2"),
                    Member(teamName = "Mobile Legends Team B", memberName = "Mobile Legends B 4", memberRole = "Mobile Legends Role 4", memberImage = "path_to_image_1"),
                    Member(teamName = "Mobile Legends Team B", memberName = "Mobile Legends B 5", memberRole = "Mobile Legends Role 5", memberImage = "path_to_image_2"),
                    Member(teamName = "Mobile Legends Team C", memberName = "Mobile Legends C 1", memberRole = "Mobile Legends Role 1", memberImage = "path_to_image_2"),
                    Member(teamName = "Mobile Legends Team C", memberName = "Mobile Legends C 2", memberRole = "Mobile Legends Role 2", memberImage = "path_to_image_1"),
                    Member(teamName = "Mobile Legends Team C", memberName = "Mobile Legends C 3", memberRole = "Mobile Legends Role 3", memberImage = "path_to_image_2"),
                    Member(teamName = "Mobile Legends Team C", memberName = "Mobile Legends C 4", memberRole = "Mobile Legends Role 4", memberImage = "path_to_image_1"),
                    Member(teamName = "Mobile Legends Team C", memberName = "Mobile Legends C 5", memberRole = "Mobile Legends Role 5", memberImage = "path_to_image_2"),

                    Member(teamName = "League of Legends Team A", memberName = "League of Legends A 1", memberRole = "League of Legends Role 1", memberImage = "path_to_image_1"),
                    Member(teamName = "League of Legends Team A", memberName = "League of Legends A 2", memberRole = "League of Legends Role 2", memberImage = "path_to_image_2"),
                    Member(teamName = "League of Legends Team A", memberName = "League of Legends A 3", memberRole = "League of Legends Role 3", memberImage = "path_to_image_1"),
                    Member(teamName = "League of Legends Team A", memberName = "League of Legends A 4", memberRole = "League of Legends Role 4", memberImage = "path_to_image_2"),
                    Member(teamName = "League of Legends Team A", memberName = "League of Legends A 5", memberRole = "League of Legends Role 5", memberImage = "path_to_image_1"),
                    Member(teamName = "League of Legends Team B", memberName = "League of Legends B 1", memberRole = "League of Legends Role 1", memberImage = "path_to_image_2"),
                    Member(teamName = "League of Legends Team B", memberName = "League of Legends B 2", memberRole = "League of Legends Role 2", memberImage = "path_to_image_1"),
                    Member(teamName = "League of Legends Team B", memberName = "League of Legends B 3", memberRole = "League of Legends Role 3", memberImage = "path_to_image_2"),
                    Member(teamName = "League of Legends Team B", memberName = "League of Legends B 4", memberRole = "League of Legends Role 4", memberImage = "path_to_image_1"),
                    Member(teamName = "League of Legends Team B", memberName = "League of Legends B 5", memberRole = "League of Legends Role 5", memberImage = "path_to_image_2"),
                    Member(teamName = "League of Legends Team C", memberName = "League of Legends C 1", memberRole = "League of Legends Role 1", memberImage = "path_to_image_2"),
                    Member(teamName = "League of Legends Team C", memberName = "League of Legends C 2", memberRole = "League of Legends Role 2", memberImage = "path_to_image_1"),
                    Member(teamName = "League of Legends Team C", memberName = "League of Legends C 3", memberRole = "League of Legends Role 3", memberImage = "path_to_image_2"),
                    Member(teamName = "League of Legends Team C", memberName = "League of Legends C 4", memberRole = "League of Legends Role 4", memberImage = "path_to_image_1"),
                    Member(teamName = "League of Legends Team C", memberName = "League of Legends C 5", memberRole = "League of Legends Role 5", memberImage = "path_to_image_2"),

                    Member(teamName = "Call of Duty Team A", memberName = "Call of Duty A 1", memberRole = "Call of Duty Role 1", memberImage = "path_to_image_1"),
                    Member(teamName = "Call of Duty Team A", memberName = "Call of Duty A 2", memberRole = "Call of Duty Role 2", memberImage = "path_to_image_2"),
                    Member(teamName = "Call of Duty Team A", memberName = "Call of Duty A 3", memberRole = "Call of Duty Role 3", memberImage = "path_to_image_1"),
                    Member(teamName = "Call of Duty Team A", memberName = "Call of Duty A 4", memberRole = "Call of Duty Role 4", memberImage = "path_to_image_2"),
                    Member(teamName = "Call of Duty Team A", memberName = "Call of Duty A 5", memberRole = "Call of Duty Role 5", memberImage = "path_to_image_1"),
                    Member(teamName = "Call of Duty Team B", memberName = "Call of Duty B 1", memberRole = "Call of Duty Role 1", memberImage = "path_to_image_2"),
                    Member(teamName = "Call of Duty Team B", memberName = "Call of Duty B 2", memberRole = "Call of Duty Role 2", memberImage = "path_to_image_1"),
                    Member(teamName = "Call of Duty Team B", memberName = "Call of Duty B 3", memberRole = "Call of Duty Role 3", memberImage = "path_to_image_2"),
                    Member(teamName = "Call of Duty Team B", memberName = "Call of Duty B 4", memberRole = "Call of Duty Role 4", memberImage = "path_to_image_1"),
                    Member(teamName = "Call of Duty Team B", memberName = "Call of Duty B 5", memberRole = "Call of Duty Role 5", memberImage = "path_to_image_2"),
                    Member(teamName = "Call of Duty Team C", memberName = "Call of Duty C 1", memberRole = "Call of Duty Role 1", memberImage = "path_to_image_2"),
                    Member(teamName = "Call of Duty Team C", memberName = "Call of Duty C 2", memberRole = "Call of Duty Role 2", memberImage = "path_to_image_1"),
                    Member(teamName = "Call of Duty Team C", memberName = "Call of Duty C 3", memberRole = "Call of Duty Role 3", memberImage = "path_to_image_2"),
                    Member(teamName = "Call of Duty Team C", memberName = "Call of Duty C 4", memberRole = "Call of Duty Role 4", memberImage = "path_to_image_1"),
                    Member(teamName = "Call of Duty Team C", memberName = "Call of Duty C 5", memberRole = "Call of Duty Role 5", memberImage = "path_to_image_2"),

                    Member(teamName = "DOTA 2 Team A", memberName = "DOTA 2 A 1", memberRole = "DOTA 2 Role 1", memberImage = "path_to_image_1"),
                    Member(teamName = "DOTA 2 Team A", memberName = "DOTA 2 A 2", memberRole = "DOTA 2 Role 2", memberImage = "path_to_image_2"),
                    Member(teamName = "DOTA 2 Team A", memberName = "DOTA 2 A 3", memberRole = "DOTA 2 Role 3", memberImage = "path_to_image_1"),
                    Member(teamName = "DOTA 2 Team A", memberName = "DOTA 2 A 4", memberRole = "DOTA 2 Role 4", memberImage = "path_to_image_2"),
                    Member(teamName = "DOTA 2 Team A", memberName = "DOTA 2 A 5", memberRole = "DOTA 2 Role 5", memberImage = "path_to_image_1"),
                    Member(teamName = "DOTA 2 Team B", memberName = "DOTA 2 B 1", memberRole = "DOTA 2 Role 1", memberImage = "path_to_image_2"),
                    Member(teamName = "DOTA 2 Team B", memberName = "DOTA 2 B 2", memberRole = "DOTA 2 Role 2", memberImage = "path_to_image_1"),
                    Member(teamName = "DOTA 2 Team B", memberName = "DOTA 2 B 3", memberRole = "DOTA 2 Role 3", memberImage = "path_to_image_2"),
                    Member(teamName = "DOTA 2 Team B", memberName = "DOTA 2 B 4", memberRole = "DOTA 2 Role 4", memberImage = "path_to_image_1"),
                    Member(teamName = "DOTA 2 Team B", memberName = "DOTA 2 B 5", memberRole = "DOTA 2 Role 5", memberImage = "path_to_image_2"),
                    Member(teamName = "DOTA 2 Team C", memberName = "DOTA 2 C 1", memberRole = "DOTA 2 Role 1", memberImage = "path_to_image_2"),
                    Member(teamName = "DOTA 2 Team C", memberName = "DOTA 2 C 2", memberRole = "DOTA 2 Role 2", memberImage = "path_to_image_1"),
                    Member(teamName = "DOTA 2 Team C", memberName = "DOTA 2 C 3", memberRole = "DOTA 2 Role 3", memberImage = "path_to_image_2"),
                    Member(teamName = "DOTA 2 Team C", memberName = "DOTA 2 C 4", memberRole = "DOTA 2 Role 4", memberImage = "path_to_image_1"),
                    Member(teamName = "DOTA 2 Team C", memberName = "DOTA 2 C 5", memberRole = "DOTA 2 Role 5", memberImage = "path_to_image_2"),

                    Member(teamName = "Fortnite Team A", memberName = "Fortnite A 1", memberRole = "Fortnite Role 1", memberImage = "path_to_image_1"),
                    Member(teamName = "Fortnite Team A", memberName = "Fortnite A 2", memberRole = "Fortnite Role 2", memberImage = "path_to_image_2"),
                    Member(teamName = "Fortnite Team A", memberName = "Fortnite A 3", memberRole = "Fortnite Role 3", memberImage = "path_to_image_1"),
                    Member(teamName = "Fortnite Team A", memberName = "Fortnite A 4", memberRole = "Fortnite Role 4", memberImage = "path_to_image_2"),
                    Member(teamName = "Fortnite Team B", memberName = "Fortnite B 1", memberRole = "Fortnite Role 1", memberImage = "path_to_image_2"),
                    Member(teamName = "Fortnite Team B", memberName = "Fortnite B 2", memberRole = "Fortnite Role 2", memberImage = "path_to_image_1"),
                    Member(teamName = "Fortnite Team B", memberName = "Fortnite B 3", memberRole = "Fortnite Role 3", memberImage = "path_to_image_2"),
                    Member(teamName = "Fortnite Team B", memberName = "Fortnite B 4", memberRole = "Fortnite Role 4", memberImage = "path_to_image_1"),
                    Member(teamName = "Fortnite Team C", memberName = "Fortnite C 1", memberRole = "Fortnite Role 1", memberImage = "path_to_image_2"),
                    Member(teamName = "Fortnite Team C", memberName = "Fortnite C 2", memberRole = "Fortnite Role 2", memberImage = "path_to_image_1"),
                    Member(teamName = "Fortnite Team C", memberName = "Fortnite C 3", memberRole = "Fortnite Role 3", memberImage = "path_to_image_2"),
                    Member(teamName = "Fortnite Team C", memberName = "Fortnite C 4", memberRole = "Fortnite Role 4", memberImage = "path_to_image_1"),
                )

                database.memberDao().insertMembers(membersList)
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
