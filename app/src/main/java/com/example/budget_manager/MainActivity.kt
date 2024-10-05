package com.example.budget_manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.budget_manager.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar.root)


        val navView: BottomNavigationView = binding.bottomNav
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController


        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.profileFragment,
                R.id.transactionsFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)

        bottomNavItemChangeListener(navView)

    }

    private fun bottomNavItemChangeListener(navView: BottomNavigationView) {
        navView.setOnItemSelectedListener {
            if (it.itemId != navView.selectedItemId) {
                navController.popBackStack(it.itemId, inclusive = true, saveState = false)
                navController.navigate(it.itemId)
            }
            true
        }
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}