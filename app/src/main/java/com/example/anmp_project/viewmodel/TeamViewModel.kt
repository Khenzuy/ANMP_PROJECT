//package com.example.anmp_project.viewmodel
//
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.anmp_project.model.EsportsDatabase
//import com.example.anmp_project.model.Team
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class TeamViewModel(application: Application) : AndroidViewModel(application) {
//    private val teamDao = EsportsDatabase.getDatabase(application).teamDao()
//
//    fun insertTeam(team: Team) {
//        viewModelScope.launch(Dispatchers.IO) {
//            teamDao.insert(team)
//        }
//    }
//
//    fun getTeamsByCompetition(competitionId: Int): List<Team> {
//        // LiveData or a similar asynchronous approach is recommended for database reads
//        return teamDao.getTeamsByCompetition(competitionId)
//    }
//}
