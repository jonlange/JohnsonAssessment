package com.jonlange.jhtassessment.workouts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonlange.data.repository.LocalWorkoutRepo
import com.jonlange.jhtassessment.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutHomeViewModel @Inject constructor(
    private val localWorkoutRepo: LocalWorkoutRepo
) : ViewModel() {

    private companion object {
        const val TAG = "WorkoutHomeScreenViewModel"
    }

    val uiState: StateFlow<WorkoutHomeUiState> = localWorkoutRepo.workouts
        .map { workouts ->
            WorkoutHomeUiState(
                workouts = workouts,
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = WorkoutHomeUiState()
        )


    init {
        // TODO: Add a way to persist workouts, so they are reset on each app launch
        if (localWorkoutRepo.workouts.value.isEmpty()) {
            resetWorkouts()
        }
    }

    fun resetWorkouts() {
        viewModelScope.launch {
            if(localWorkoutRepo.workouts.value.isEmpty()) {
                Log.d(TAG, "Resetting workouts")
                localWorkoutRepo.loadWorkoutsFromRaw(R.raw.workouts)
            }
        }
    }

}