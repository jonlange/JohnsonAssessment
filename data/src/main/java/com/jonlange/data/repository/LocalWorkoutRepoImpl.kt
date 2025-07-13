package com.jonlange.data.repository

import com.jonlange.core.ResourceLoader
import com.jonlange.data.model.Workout
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.builtins.ListSerializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalWorkoutRepoImpl @Inject constructor(
    private val resourceLoader: ResourceLoader
) : LocalWorkoutRepo {

    companion object {
        private const val TAG = "LocalWorkoutRepo"
    }

    private val _workouts = MutableStateFlow<ImmutableList<Workout>>(persistentListOf())
    override val workouts = _workouts.asStateFlow()

    override suspend fun updateWorkouts() {
    }

    // IF we do not want to pass in the resId, we can move the JSON to the data module, but I left
    // it like this for clarity.
    override suspend fun loadWorkoutsFromRaw(rawResId: Int) {
        // Load from JSON file from res provided by app.
        val workouts = resourceLoader.loadJSONFromRaw(
            rawResId, ListSerializer(Workout.serializer())
        )

        _workouts.value = workouts.toImmutableList()
    }
}