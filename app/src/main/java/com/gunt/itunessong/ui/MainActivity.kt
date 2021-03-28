package com.gunt.itunessong.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.gunt.itunessong.BR
import com.gunt.itunessong.R
import com.gunt.itunessong.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.setVariable(BR.mainViewModel, viewModel)
        binding.executePendingBindings()
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        navController = navHostFragment.navController

        setupBottomNavigationSelectedListener()
    }

    private fun setupBottomNavigationSelectedListener() {
        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.song -> {
                    navController.setGraph(R.navigation.nav_songs)
                    setupActionBarWithNavController(navController)
                }
                R.id.favorite -> {
                    navController.setGraph(R.navigation.nav_favorites)
                    setupActionBarWithNavController(navController)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

}
