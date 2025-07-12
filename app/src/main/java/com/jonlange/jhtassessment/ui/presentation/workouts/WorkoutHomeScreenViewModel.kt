package com.jonlange.jhtassessment.ui.presentation.workouts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonlange.data.repository.LocalWorkoutRepo
import com.jonlange.jhtassessment.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutHomeScreenViewModel @Inject constructor(
    private val localWorkoutRepo: LocalWorkoutRepo
) : ViewModel() {

    private companion object {
        const val TAG = "WorkoutHomeScreenViewModel"
    }

    init {
        viewModelScope.launch {
            localWorkoutRepo.loadWorkoutsFromRaw(R.raw.workouts)
        }
    }


}