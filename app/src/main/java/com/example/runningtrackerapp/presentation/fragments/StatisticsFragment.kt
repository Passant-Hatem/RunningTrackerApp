package com.example.runningtrackerapp.presentation.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.runningtrackerapp.R
import com.example.runningtrackerapp.presentation.viewmodels.StatisticsViewModel


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val viewModel: StatisticsViewModel by viewModels()
}