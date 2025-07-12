package com.jonlange.data.repository

import android.util.Log
import com.jonlange.core.ResourceLoader
import com.jonlange.core.model.Workout
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject


class LocalWorkoutRepoImpl @Inject constructor(
    private val resourceLoader: ResourceLoader
) : LocalWorkoutRepo {

    companion object {
        private const val TAG = "LocalWorkoutRepo"
    }

    private val _workouts = MutableStateFlow<ImmutableList<Workout>?>(null)
    override val workouts = _workouts.asStateFlow()

    override suspend fun updateWorkouts() {
    }

    // IF we do not want to pass in the resId, we can move the JSON to the data module, but I left
    // it like this for clarity.
    override suspend fun loadWorkoutsFromRaw(rawResId: Int) {
        // Load from JSON file from res provided by app.
        val workouts: List<Workout> = resourceLoader.loadJSONFromRaw(
            rawResId, ListSerializer(Workout.serializer())
        )

        Log.d(TAG, "$workouts")
    }
}