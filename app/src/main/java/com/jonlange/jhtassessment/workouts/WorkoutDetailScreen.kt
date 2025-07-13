package com.jonlange.jhtassessment.workouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jonlange.data.model.Workout

@Composable
fun WorkoutDetailScreen(
    workoutId: String,
    onNavigateBack: () -> Unit) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Workout Details for $workoutId",
            style = MaterialTheme.typography.headlineLarge
        )
    }

}