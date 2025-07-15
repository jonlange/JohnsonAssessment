package com.jonlange.jhtassessment.workouts.detail

import com.jonlange.data.model.Workout

/**
 * Simple ui state that will facilitate editing and update workouts.
 */
data class WorkoutDetailUiState(
    // Immutable from Repo
    val workout: Workout? = null,
    // Mutable until the user saves or cancels
    val editedWorkout: Workout? = null,
    val isEditing: Boolean = false,
    val isSaving: Boolean = false,
    val errorMessage: String? = null,
)