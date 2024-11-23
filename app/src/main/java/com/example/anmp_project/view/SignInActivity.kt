package com.example.anmp_project.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.anmp_project.databinding.ActivitySignInBinding
import com.example.anmp_project.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var userDatabase: UserDatabase
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDatabase = UserDatabase.buildDatabase(this)
        sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)

        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            navigateToMainActivity()
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loginUser(username, password)
        }

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = userDatabase.userDao().selectAllUsers().find { it.username == username && it.password == password }

            if (user != null) {
                saveSession(username)
                runOnUiThread {
                    Toast.makeText(this@SignInActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                    navigateToMainActivity()
                }
            } else {
                runOnUiThread {
                    Toast.makeText(this@SignInActivity, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveSession(username: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Tutup SignInActivity agar pengguna tidak dapat kembali
    }
}
