package com.example.runningtrackerapp.data.repository

import androidx.lifecycle.LiveData
import com.example.runningtrackerapp.data.local.RunningDao
import com.example.runningtrackerapp.domain.model.Run
import com.example.runningtrackerapp.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(
    private val runningDao: RunningDao
): MainRepository{
    override suspend fun insertRun(run: Run) = runningDao.insertRun(run)

    override suspend fun deleteRun(run: Run) = runningDao.deleteRun(run)

    override fun getAllRunsSortedByDate(): LiveData<List<Run>> = runningDao.getAllRunsSortedByDate()

    override fun getAllRunsSortedByDistance(): LiveData<List<Run>> = runningDao.getAllRunsSortedByDistance()

    override fun getAllRunsSortedByTimeInMillis(): LiveData<List<Run>> = runningDao.getAllRunsSortedByTimeInMillis()

    override fun getAllRunsSortedByAvgSpeed(): LiveData<List<Run>> = runningDao.getAllRunsSortedByAvgSpeed()

    override fun getAllRunsSortedByCaloriesBurned(): LiveData<List<Run>> = runningDao.getAllRunsSortedByCaloriesBurned()

    override fun getTotalAvgSpeed(): LiveData<Float> = runningDao.getTotalAvgSpeed()

    override fun getTotalDistance(): LiveData<Int> = runningDao.getTotalDistance()

    override fun getTotalCaloriesBurned(): LiveData<Int> = runningDao.getTotalCaloriesBurned()

    override fun getTotalTimeInMillis(): LiveData<Long> = runningDao.getTotalTimeInMillis()
}