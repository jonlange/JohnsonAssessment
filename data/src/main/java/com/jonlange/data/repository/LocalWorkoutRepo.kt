package com.jonlange.data.repository

import com.jonlange.data.model.Workout
import kotlinx.coroutines.flow.StateFlow

interface LocalWorkoutRepo {

    val workouts: StateFlow<List<Workout>>

    suspend fun updateWorkout(updatedWorkout: Workout)

    suspend fun loadWorkoutsFromRaw(rawResId: Int)
}