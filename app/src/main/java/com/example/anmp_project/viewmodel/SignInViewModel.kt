package com.example.anmp_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anmp_project.model.User
import com.example.anmp_project.model.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(private val userDao: UserDao) : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    fun loginUser(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            _errorMessage.value = "Please fill in all fields"
            return
        }

        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                userDao.getUserByUsername(username)
            }

            if (user != null && user.password == password) {
                _user.postValue(user)
                _loginResult.postValue(true)
            } else {
                _errorMessage.postValue("Invalid username or password")
                _loginResult.postValue(false)
            }
        }
    }

    fun resetErrorMessage() {
        _errorMessage.value = null
    }
}
