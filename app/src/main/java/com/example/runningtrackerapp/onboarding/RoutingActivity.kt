package com.example.runningtrackerapp.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.runningtrackerapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routing)

        supportActionBar?.hide()

    }
}