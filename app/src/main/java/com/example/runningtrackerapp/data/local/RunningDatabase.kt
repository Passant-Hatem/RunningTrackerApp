package com.example.runningtrackerapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.runningtrackerapp.domain.model.Run

@Database(
    entities = [Run::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class RunningDatabase : RoomDatabase() {
    abstract fun getRunDao(): RunningDao
}