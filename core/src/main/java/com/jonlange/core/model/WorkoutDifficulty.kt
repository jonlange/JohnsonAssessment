package com.jonlange.core.model

import kotlinx.serialization.Serializable

@Serializable
enum class WorkoutDifficulty {
    Unknown,
    Beginner,
    Intermediate,
    Advanced
}