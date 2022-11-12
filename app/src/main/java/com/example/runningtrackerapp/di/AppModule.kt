package com.example.runningtrackerapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.runningtrackerapp.data.local.RunningDatabase
import com.example.runningtrackerapp.data.repository.MainRepositoryImp
import com.example.runningtrackerapp.domain.repository.MainRepository
import com.example.runningtrackerapp.util.Constants.KEY_FIRST_TIME_TOGGLE
import com.example.runningtrackerapp.util.Constants.KEY_NAME
import com.example.runningtrackerapp.util.Constants.KEY_WEIGHT
import com.example.runningtrackerapp.util.Constants.RUNNING_DATABASE_NAME
import com.example.runningtrackerapp.util.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        RunningDatabase::class.java,
        RUNNING_DATABASE_NAME
    ).build()


    @Singleton
    @Provides
    fun provideMainRepository(database: RunningDatabase): MainRepository{
        return MainRepositoryImp(database.getRunDao())
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPref: SharedPreferences) = sharedPref.getString(KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideWeight(sharedPref: SharedPreferences) = sharedPref.getFloat(KEY_WEIGHT, 80f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPref: SharedPreferences) =
        sharedPref.getBoolean(KEY_FIRST_TIME_TOGGLE, true)

}
