package com.example.runningtrackerapp.service

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LifecycleService
import com.example.runningtrackerapp.util.Constants.ACTION_PAUSE_SERVICE
import com.example.runningtrackerapp.util.Constants.ACTION_START_OR_RESUME_SERVICE
import com.example.runningtrackerapp.util.Constants.ACTION_STOP_SERVICE

class TrackingService :  LifecycleService() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when(it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
                    Log.d("Location Service","Started or resumed service")
                }
                ACTION_PAUSE_SERVICE -> {
                    Log.d("Location Service","Paused service")
                }
                ACTION_STOP_SERVICE -> {
                    Log.d("Location Service","Stopped service")
                }
                else -> {

                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
}