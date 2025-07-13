package com.jonlange.jhtassessment.ui.presentation.workouts

import com.jonlange.data.model.Workout

data class WorkoutHomeScreenUiState(
    val isRefreshing: Boolean = false,
    val workouts: List<Workout> = emptyList()
    // TODO: If desired we could add specific filtering here
) {
    val hasWorkouts: Boolean
        get() = workouts.isNotEmpty()
}