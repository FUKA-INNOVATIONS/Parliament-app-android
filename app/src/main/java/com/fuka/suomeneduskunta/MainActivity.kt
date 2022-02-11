package com.fuka.suomeneduskunta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.fuka.suomeneduskunta.databinding.ActivityMainBinding
import com.fuka.suomeneduskunta.ui.fragments.HomeFragment
import com.fuka.suomeneduskunta.ui.fragments.HomeFragmentDirections
import com.fuka.suomeneduskunta.ui.fragments.PartiesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Navigation and action bar setup
        navController = Navigation.findNavController(this, R.id.NavHostFragment)
        binding.bottomNavigation.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    // setup up/back button
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

}

