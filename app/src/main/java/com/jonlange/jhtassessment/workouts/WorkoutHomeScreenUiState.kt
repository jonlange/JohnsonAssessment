package com.jonlange.jhtassessment.workouts

import com.jonlange.data.model.Workout

data class WorkoutHomeScreenUiState(
    val workouts: List<Workout> = emptyList()
    // TODO: If desired we could add specific filtering here
) {
    val hasWorkouts: Boolean
        get() = workouts.isNotEmpty()
}