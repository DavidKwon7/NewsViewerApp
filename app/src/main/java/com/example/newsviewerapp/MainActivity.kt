package com.example.newsviewerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsviewerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // tool bar
        setSupportActionBar(binding.toolBar)

        // bottom nav
        // 아이템 색 바꾸기
        // https://lcw126.tistory.com/330
        val navHostFragment = supportFragmentManager.findFragmentById(
            androidx.navigation.fragment.R.id.nav_host_fragment_container
        ) as NavHostFragment

        val navController = navHostFragment.findNavController()
        binding.bnMenu.setupWithNavController(navController)
    }
}