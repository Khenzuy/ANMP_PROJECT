package com.example.anmp_project.model

import androidx.room.*

@Dao
interface AchievementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAchievement(achievement: Achievement)

    @Query("DELETE FROM achievements")
    fun deleteAll()

    @Query("SELECT * FROM achievements WHERE competition_id = :competitionId")
    fun getAchievementsForCompetition(competitionId: Int): List<Achievement>
}