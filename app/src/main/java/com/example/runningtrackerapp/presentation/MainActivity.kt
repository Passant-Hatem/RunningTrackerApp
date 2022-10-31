package com.example.runningtrackerapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.runningtrackerapp.R
import com.example.runningtrackerapp.databinding.ActivityMainBinding
import com.example.runningtrackerapp.util.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //set up navigation
        navController = setupNavController()
        attachNavUIToController(navController)
        navController.addOnDestinationChangedListener{_ ,destination, _ ->
            when(destination.id){
                R.id.settingsFragment, R.id.runFragment, R.id.statisticsFragment ->
                 binding.bottomNavigationView.visibility = View.VISIBLE
                else -> binding.bottomNavigationView.visibility = View.GONE
            }
        }
        navigateToTrackingFragmentIfNeeded(intent)
    }

    private fun attachNavUIToController(navController: NavController) {
        val navView: BottomNavigationView = binding.bottomNavigationView

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.runFragment,
                R.id.statisticsFragment,
                R.id.settingsFragment,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun setupNavController(): NavController {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        return navHostFragment.navController
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }

    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?) {
        if(intent?.action == ACTION_SHOW_TRACKING_FRAGMENT) {
          navController.navigate(R.id.action_global_trackingFragment)
        }
    }
}