package com.example.anmp_project.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.anmp_project.model.EsportsDatabase
import com.example.anmp_project.model.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamViewModel(application: Application) : AndroidViewModel(application) {

    private val teamDao = EsportsDatabase.getDatabase(application, CoroutineScope(Dispatchers.IO)).teamDao()
    lateinit var teams: LiveData<List<Team>>

    fun getTeamsByCompetition(competitionId: Int) {
        teams = teamDao.getTeamsByCompetition(competitionId)
    }
}

