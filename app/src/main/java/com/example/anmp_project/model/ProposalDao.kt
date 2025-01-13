package com.example.anmp_project.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProposalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProposal(proposal: Proposal)

    @Query("SELECT * FROM proposal WHERE username = :username")
    fun getProposalsByUsernameSync(username: String): List<Proposal>

    @Query("SELECT * FROM proposal WHERE username = :username")
    fun getProposalsByUsername(username: String): LiveData<List<Proposal>>
}
