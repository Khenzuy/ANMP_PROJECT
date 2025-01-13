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

class ApplyTeamListViewModel(application: Application) : AndroidViewModel(application) {

    private val database = EsportsDatabase.getDatabase(application, viewModelScope)
    private val _proposals = MutableLiveData<List<Proposal>>()
    val proposals: LiveData<List<Proposal>> get() = _proposals

    fun refresh(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val proposalList = database.proposalDao().getProposalsByUsernameSync(username)
                _proposals.postValue(proposalList)
            } catch (e: Exception) {
                e.printStackTrace()
                _proposals.postValue(emptyList())
            }
        }
    }
}
