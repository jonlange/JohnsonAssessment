package com.jonlange.data.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import com.jonlange.data.R
import kotlinx.serialization.Serializable

// Serializable enum with additional data
enum class WorkoutDifficulty {
    Unknown, Beginner, Intermediate, Advanced;

    val icon: ImageVector
        get() = when (this) {
            Unknown -> Icons.Default.Edit
            Beginner -> Icons.Default.ThumbUp
            Intermediate -> Icons.Default.Star
            Advanced -> Icons.Default.Lock
        }

    val color: Color
        get() = when (this) {
            Unknown -> Color.LightGray.copy(alpha = 0.5f)
            Beginner -> Color.Green.copy(alpha = 0.5f).compositeOver(Color.White)
            Intermediate -> Color.Yellow.copy(alpha = 0.5f).compositeOver(Color.White)
            Advanced -> Color.Red.copy(alpha = 0.5f).compositeOver(Color.White)
        }

    @get:StringRes
    val labelRes: Int
        get() = when (this) {
            Unknown -> R.string.workout_difficulty_unknown
            Beginner -> R.string.workout_difficulty_beginner
            Intermediate -> R.string.workout_difficulty_intermediate
            Advanced -> R.string.workout_difficulty_Advanced
        }
}