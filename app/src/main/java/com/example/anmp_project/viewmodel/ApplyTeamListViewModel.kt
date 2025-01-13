package com.example.anmp_project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.anmp_project.model.EsportsDatabase
import com.example.anmp_project.model.Proposal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ApplyTeamListViewModel(application: Application) : AndroidViewModel(application) {

    private val database = EsportsDatabase.getDatabase(application, viewModelScope)
    var proposals: LiveData<List<Proposal>> = MutableLiveData()

    fun refresh(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            proposals = database.proposalDao().getProposalsByUsername(username)
        }
    }
}



