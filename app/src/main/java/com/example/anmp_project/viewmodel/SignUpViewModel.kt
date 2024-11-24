package com.example.anmp_project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.anmp_project.model.User
import com.example.anmp_project.model.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = UserDatabase.buildDatabase(application).userDao()

    val userRegistrationStatus = MutableLiveData<String>()

    fun registerUser(firstName: String, lastName: String, username: String, password: String, repeatPassword: String) {
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            userRegistrationStatus.postValue("Please fill all fields")
            return
        }

        if (password != repeatPassword) {
            userRegistrationStatus.postValue("Passwords do not match")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val existingUser = userDao.getUserByUsername(username)

            if (existingUser != null) {
                userRegistrationStatus.postValue("Username already exists")
            } else {
                val newUser = User(firstName, lastName, username, password)
                userDao.insertAll(newUser)

                userRegistrationStatus.postValue("User Registered Successfully")
            }
        }
    }
}
