package com.jonlange.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Workout (
    val id: String,
    val name: String = "",
    val equipment: String = "",
    val duration: Int = 0,
    val difficulty: WorkoutDifficulty? = WorkoutDifficulty.Unknown
)