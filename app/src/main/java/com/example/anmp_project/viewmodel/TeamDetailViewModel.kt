package com.example.anmp_project.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.anmp_project.model.EsportsDatabase
import com.example.anmp_project.model.Member
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val memberDao = EsportsDatabase.getDatabase(application, CoroutineScope(Dispatchers.IO)).memberDao()
    lateinit var members: LiveData<List<Member>>

    fun getMembersByTeamName(teamName: String) {
        members = memberDao.getMembersByTeamName(teamName)
    }
}