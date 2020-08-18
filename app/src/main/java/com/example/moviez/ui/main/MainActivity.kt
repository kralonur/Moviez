package com.example.moviez.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.moviez.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setUpBottomNav(navController, bottomNavigationView)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    private fun setUpBottomNav(
        controller: NavController,
        bottomNavigationView: BottomNavigationView
    ) {
        controller.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movieFragment, R.id.searchFragment, R.id.starFragment, R.id.TVFragment ->
                    showBottomNavigation(bottomNavigationView)
                else -> hideBottomNavigation(bottomNavigationView)
            }
        }
    }

    private fun hideBottomNavigation(bottom_navigation: BottomNavigationView) {
        with(bottom_navigation) {
            if (visibility == View.VISIBLE && alpha == 1f) {
                animate()
                    .alpha(0f)
                    .withEndAction { visibility = View.GONE }
                    .duration = 500
            }
        }
    }

    private fun showBottomNavigation(bottom_navigation: BottomNavigationView) {
        with(bottom_navigation) {
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .duration = 500
        }
    }

}