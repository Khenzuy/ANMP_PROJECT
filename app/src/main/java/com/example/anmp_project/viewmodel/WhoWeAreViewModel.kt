package com.example.anmp_project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.anmp_project.model.EsportsDatabase
import com.example.anmp_project.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WhoWeAreViewModel(application: Application) : AndroidViewModel(application) {
    private val db = EsportsDatabase.getDatabase(application, viewModelScope)
    private val userDao = db.userDao()

    fun getUserData(username: String): LiveData<User> {
        return userDao.getUserByUsernameLive(username)
    }

    fun incrementLike(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.incrementLikeCount(username)
        }
    }
}
