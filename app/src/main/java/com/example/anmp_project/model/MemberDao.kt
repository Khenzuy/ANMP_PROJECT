package com.example.anmp_project.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemberDao {

    @Query("SELECT * FROM members WHERE teamName = :teamName")
    fun getMembersByTeamName(teamName: String): LiveData<List<Member>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMembers(members: List<Member>)
}