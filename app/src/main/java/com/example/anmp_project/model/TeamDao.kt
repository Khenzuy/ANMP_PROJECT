package com.example.anmp_project.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(teams: List<Team>)

    @Query("SELECT * FROM teams WHERE competitionId = :competitionId")
    fun getTeamsByCompetition(competitionId: Int): LiveData<List<Team>>

    // Add a method to retrieve teams based on the game name by joining with competitions
    @Query("SELECT t.* FROM teams t JOIN competitions c ON t.competitionId = c.id WHERE c.game_name = :gameName")
    fun getTeamsByGameName(gameName: String): LiveData<List<Team>>
}
