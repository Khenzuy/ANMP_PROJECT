package com.example.anmp_project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.anmp_project.model.Competition
import com.example.anmp_project.model.EsportsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WhatWePlayViewModel(application: Application) : AndroidViewModel(application) {
    private val db = EsportsDatabase.getDatabase(application, viewModelScope)
    val competitionsLD: LiveData<List<Competition>> = db.competitionDao().getAllCompetitionsLiveData()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorState = MutableLiveData<Boolean>()
    val errorState: LiveData<Boolean> get() = _errorState

    init {
        refresh()
    }

    fun refresh() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val competitions = db.competitionDao().getAllCompetitionsLiveData().value
                _errorState.postValue(false)
                competitions?.let {
                    viewModelScope.launch(Dispatchers.Main) {
                        (competitionsLD as MutableLiveData).postValue(it)
                    }
                }
            } catch (exception: Exception) {
                _errorState.postValue(true)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
