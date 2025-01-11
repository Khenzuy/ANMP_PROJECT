package com.example.anmp_project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.anmp_project.model.Competition
import com.example.anmp_project.model.EsportsDatabase
import com.example.anmp_project.model.Proposal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApplyTeamSubmitViewModel(application: Application) : AndroidViewModel(application) {

    private val database = EsportsDatabase.getDatabase(application, viewModelScope)
    private val _competitions = MutableLiveData<List<Competition>>()
    val competitions: LiveData<List<Competition>> = _competitions

    init {
        loadCompetitions()
    }

    fun loadCompetitions() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val competitionList = database.competitionDao().getAllCompetitions()
                _competitions.postValue(competitionList)
            } catch (e: Exception) {
                e.printStackTrace()
                _competitions.postValue(emptyList())
            }
        }
    }

    fun loadTeams(gameName: String): LiveData<List<com.example.anmp_project.model.Team>> {
        return database.teamDao().getTeamsByGameName(gameName)
    }

    fun submitProposal(proposal: Proposal) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                database.proposalDao().insertProposal(proposal)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
