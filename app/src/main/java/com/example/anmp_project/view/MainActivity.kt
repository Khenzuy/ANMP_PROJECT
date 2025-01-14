package com.example.anmp_project.view

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.anmp_project.R
import com.example.anmp_project.databinding.ActivityMainBinding
import com.example.anmp_project.model.EsportsDatabase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeDatabase()

        sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.itemWhatWePlay, R.id.itemOurSchedule, R.id.itemWhoWeAre),
            binding.drawerLayout
        )

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        binding.bottomNav.setupWithNavController(navController)

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_apply_team -> {
                    navController.popBackStack(R.id.applyTeamListFragment, true)
                    navController.navigate(R.id.applyTeamListFragment)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_logout -> {
                    logout()
                    true
                }
                else -> {
                    NavigationUI.onNavDestinationSelected(menuItem, navController)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
            }
        }

        val tvWelcomeMessage = binding.navView.getHeaderView(0).findViewById<TextView>(R.id.tvWelcomeMessage)
        val imageProfile = binding.navView.getHeaderView(0).findViewById<ImageView>(R.id.imageProfile)

        val firstName = sharedPreferences.getString("first_name", "User")
        val lastName = sharedPreferences.getString("last_name", "Anonymous")
        val profileImageUri = sharedPreferences.getString("profile_image", null)

        tvWelcomeMessage.text = "Welcome, $firstName $lastName"

        if (!profileImageUri.isNullOrEmpty()) {
            Picasso.get().load(profileImageUri).placeholder(R.drawable.logo_esport).into(imageProfile)
        } else {
            imageProfile.setImageResource(R.drawable.logo_esport)
        }
    }

    private fun initializeDatabase() {
        val scope = CoroutineScope(Dispatchers.IO)
        EsportsDatabase.getDatabase(this, scope)
    }

    private fun logout() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }
}
