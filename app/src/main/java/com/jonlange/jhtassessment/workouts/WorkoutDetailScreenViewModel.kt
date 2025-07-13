package com.jonlange.jhtassessment.workouts

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonlange.data.model.Workout
import com.jonlange.data.repository.LocalWorkoutRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailScreenViewModel @Inject constructor(
    private val localWorkoutRepo: LocalWorkoutRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private companion object {
        const val TAG = "WorkoutDetailScreenViewModel"
    }

    private val workoutId: String = checkNotNull(savedStateHandle["workoutId"]) {
        "missing workoutId"
    }

    val workout: StateFlow<Workout?> = localWorkoutRepo.workouts
        .map { workouts ->
            workouts.firstOrNull { it.id == workoutId }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )


    init {

        Log.d(TAG, "Repo instance = ${localWorkoutRepo.hashCode()}")

        Log.d(TAG, "********* workoutId: $workoutId")

        viewModelScope.launch {
            localWorkoutRepo.workouts.collect { workouts ->
                Log.d(TAG, "Repo workouts: $workouts")
                val workout = workouts?.firstOrNull { it.id == workoutId}
                Log.d(TAG, "Workout Found: $workout")
            }
        }

    }



}