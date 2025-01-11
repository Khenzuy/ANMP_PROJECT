package com.example.anmp_project.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CompetitionDao {
    @Query("SELECT * FROM competitions")
    fun getAllCompetitionsLiveData(): LiveData<List<Competition>>

    @Query("SELECT * FROM competitions")
    fun getAllCompetitions(): List<Competition>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompetition(competition: Competition)

    @Query("DELETE FROM competitions")
    fun deleteAll()
}
