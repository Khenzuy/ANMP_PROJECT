package com.example.anmp_project.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.anmp_project.databinding.ActivitySignInBinding
import com.example.anmp_project.model.EsportsDatabase
import com.example.anmp_project.viewmodel.SignInViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var signInViewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)

        val userDao = EsportsDatabase.getDatabase(this, CoroutineScope(Dispatchers.Main)).userDao()
        signInViewModel = SignInViewModel(userDao)

        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            navigateToMainActivity()
        }

        setupObservers()

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            signInViewModel.loginUser(username, password)
        }

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupObservers() {
        signInViewModel.loginResult.observe(this) { isSuccess ->
            if (isSuccess) {
                val user = signInViewModel.user.value
                user?.let {
                    saveSession(it.username, it.firstName, it.lastName, it.profileImage)
                }
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                navigateToMainActivity()
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }

        signInViewModel.errorMessage.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                signInViewModel.resetErrorMessage()
            }
        }
    }

    private fun saveSession(username: String, firstName: String, lastName: String, profileImage: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("first_name", firstName)
        editor.putString("last_name", lastName)
        editor.putString("profile_image", profileImage)
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
