package com.example.anmp_project.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.anmp_project.databinding.ActivitySignUpBinding
import com.example.anmp_project.model.User
import com.example.anmp_project.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var userDatabase: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDatabase = UserDatabase.buildDatabase(this)

        binding.btnRegister.isEnabled = false

        binding.cbTerms.setOnCheckedChangeListener { _, isChecked ->
            binding.btnRegister.isEnabled = isChecked
        }

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun registerUser() {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val repeatPassword = binding.etRepeatPassword.text.toString().trim()

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != repeatPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            val existingUser = userDatabase.userDao().getUserByUsername(username)

            if (existingUser != null) {
                runOnUiThread {
                    Toast.makeText(this@SignUpActivity, "Username already exists", Toast.LENGTH_SHORT).show()
                }
            } else {
                val newUser = User(firstName, lastName, username, password)
                userDatabase.userDao().insertAll(newUser)

                runOnUiThread {
                    Toast.makeText(this@SignUpActivity, "User Registered Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                    startActivity(intent)
                    finish()

                }
            }
        }
    }
}
