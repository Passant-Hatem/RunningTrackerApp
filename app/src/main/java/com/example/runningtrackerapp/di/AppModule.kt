package com.example.runningtrackerapp.di

import android.content.Context
import androidx.room.Room
import com.example.runningtrackerapp.data.local.RunningDatabase
import com.example.runningtrackerapp.data.repository.MainRepositoryImp
import com.example.runningtrackerapp.domain.repository.MainRepository
import com.example.runningtrackerapp.util.Constants.RUNNING_DATABASE_NAME
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
}
