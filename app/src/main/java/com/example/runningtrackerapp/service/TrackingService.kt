package com.example.runningtrackerapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.example.runningtrackerapp.R
import com.example.runningtrackerapp.presentation.MainActivity
import com.example.runningtrackerapp.util.Constants.ACTION_PAUSE_SERVICE
import com.example.runningtrackerapp.util.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import com.example.runningtrackerapp.util.Constants.ACTION_START_OR_RESUME_SERVICE
import com.example.runningtrackerapp.util.Constants.ACTION_STOP_SERVICE
import com.example.runningtrackerapp.util.Constants.NOTIFICATION_CHANNEL_ID
import com.example.runningtrackerapp.util.Constants.NOTIFICATION_CHANNEL_NAME
import com.example.runningtrackerapp.util.Constants.NOTIFICATION_ID

class TrackingService :  LifecycleService() {

    var isFirstRun = true

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when(it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
                    if(isFirstRun) {
                        startForegroundService()
                        isFirstRun = false
                    } else {
                        Log.d("Location Service","Service is running")
                    }
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

    private fun startForegroundService(){
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        createNotificationChannel(notificationManager)

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_directions_run_black_24dp)
            .setContentTitle("Running App")
            .setContentText("00:00:00")
            .setContentIntent(getMainActivityPendingIntent())

        startForeground(NOTIFICATION_ID, notificationBuilder.build())

    }

    private fun getMainActivityPendingIntent() = PendingIntent.getActivity(
        this,
        0,
        Intent(this, MainActivity::class.java).also {
            it.action = ACTION_SHOW_TRACKING_FRAGMENT
        },
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }
}