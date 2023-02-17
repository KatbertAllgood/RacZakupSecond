package com.example.raczakupsecond.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_RacZakupSecond)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}