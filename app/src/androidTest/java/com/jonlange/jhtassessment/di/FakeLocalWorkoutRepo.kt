package com.jonlange.jhtassessment.di

import com.jonlange.data.model.Workout
import com.jonlange.data.repository.LocalWorkoutRepo
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class FakeLocalWorkoutRepo @Inject constructor() : LocalWorkoutRepo {

    private val _workouts = MutableStateFlow<ImmutableList<Workout>>(persistentListOf())
    override val workouts = _workouts.asStateFlow()

    override suspend fun updateWorkout(updatedWorkout: Workout) {
        _workouts.update { currentWorkouts ->
            currentWorkouts.map { workout ->
                if (workout.id == updatedWorkout.id) updatedWorkout else workout
            }.toImmutableList()
        }
    }

    // IF we do not want to pass in the resId, we can move the JSON to the data module, but I left
    // it like this for clarity.
    override suspend fun loadWorkoutsFromRaw(rawResId: Int) {
        // no op
    }

    // Allows us to manually add values to test with
    fun createWorkouts(workouts: List<Workout>) {
        _workouts.value = workouts.toImmutableList()
    }
}