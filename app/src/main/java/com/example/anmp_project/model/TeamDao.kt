package com.example.anmp_project.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TeamDao {

    @Query("SELECT * FROM teams WHERE competitionId = :competitionId")
    fun getTeamsByCompetition(competitionId: Int): LiveData<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeams(teams: List<Team>)

    @Delete
    fun deleteTeam(team: Team)

    @Query("SELECT t.* FROM teams t JOIN competitions c ON t.competitionId = c.id WHERE c.game_name = :gameName")
    fun getTeamsByGameName(gameName: String): LiveData<List<Team>>
}

