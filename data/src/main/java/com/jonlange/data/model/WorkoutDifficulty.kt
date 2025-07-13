package com.jonlange.data.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.Color
import com.jonlange.data.R
import kotlinx.serialization.Serializable

@Serializable
enum class WorkoutDifficulty(
    val icon: ImageVector,
    val color: Color,
    @StringRes val labelRes: Int
) {
    Unknown(
        icon = Icons.Default.Edit,
        color = Color.LightGray,
        labelRes = R.string.workout_difficulty_unknown
    ),
    Beginner(
        icon = Icons.Default.ThumbUp,
        color = Color.Green,
        labelRes = R.string.workout_difficulty_beginner
    ),
    Intermediate(
        icon = Icons.Default.Star,
        color = Color.Yellow,
        labelRes = R.string.workout_difficulty_intermediate
    ),
    Advanced(
        icon = Icons.Default.Lock,
        color = Color.Red,
        labelRes = R.string.workout_difficulty_Advanced
    ),
}