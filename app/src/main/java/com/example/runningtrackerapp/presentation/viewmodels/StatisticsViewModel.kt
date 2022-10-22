package com.example.runningtrackerapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.runningtrackerapp.domain.repository.MainRepository
import javax.inject.Inject

class StatisticsViewModel @Inject constructor(
    val mainRepository: MainRepository
): ViewModel() {
}