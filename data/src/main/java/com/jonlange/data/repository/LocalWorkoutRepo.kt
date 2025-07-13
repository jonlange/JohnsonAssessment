package com.jonlange.data.repository

import com.jonlange.data.model.Workout
import kotlinx.coroutines.flow.StateFlow

interface LocalWorkoutRepo {

    val workouts: StateFlow<List<Workout>?>

    suspend fun updateWorkouts()

    suspend fun loadWorkoutsFromRaw(rawResId: Int): List<Workout>
}