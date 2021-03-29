package com.gunt.itunessong.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gunt.itunessong.BR
import com.gunt.itunessong.R
import com.gunt.itunessong.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.setVariable(BR.mainViewModel, viewModel)
        binding.executePendingBindings()
        setContentView(binding.root)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.botttom_navigation)
        bottomNavigationView.setupWithNavController(findNavController(R.id.container))
    }
}
