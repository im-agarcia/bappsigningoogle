package com.example.smoketest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smoketest.R
import com.example.smoketest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}