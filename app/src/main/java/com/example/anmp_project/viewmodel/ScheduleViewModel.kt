package com.example.anmp_project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.anmp_project.model.Schedule
import com.example.anmp_project.model.EsportsDatabase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScheduleViewModel(application: Application) : AndroidViewModel(application) {
    private val db = EsportsDatabase.getDatabase(application, viewModelScope)
    private val gson = Gson()
    val schedulesLD: LiveData<List<Schedule>> = db.scheduleDao().getAllSchedules()

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
                val schedules = db.scheduleDao().getAllSchedules().value
                _errorState.postValue(false)
                schedules?.let {
                    viewModelScope.launch(Dispatchers.Main) {
                        (schedulesLD as MutableLiveData<List<Schedule>>).postValue(it)
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
