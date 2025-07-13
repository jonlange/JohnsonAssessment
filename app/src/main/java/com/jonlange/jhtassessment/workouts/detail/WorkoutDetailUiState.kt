package com.jonlange.jhtassessment.workouts.detail

import com.jonlange.data.model.Workout

/**
 * Simple ui state that will facilitate editing and update workouts.
 */
data class WorkoutDetailUiState(
    val workout: Workout? = null,
    val isEditing: Boolean = false,
    val isSaving: Boolean = false,
    val errorMessage: String? = null,
)