package com.example.runningtrackerapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runningtrackerapp.domain.model.Run
import com.example.runningtrackerapp.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }
}