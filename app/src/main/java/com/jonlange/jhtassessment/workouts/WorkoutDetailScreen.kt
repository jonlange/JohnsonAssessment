package com.jonlange.jhtassessment.workouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun WorkoutDetailScreen(
    viewModel: WorkoutDetailScreenViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {

    val workout by viewModel.workout.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        workout?.let {
            Text(
                text = "Workout: ${it.name}",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Difficulty: ${stringResource(it.difficulty.labelRes)}"
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Duration: ${it.duration} minutes"
            )
            Spacer(Modifier.height(8.dp))
            if (it.equipment.isEmpty()) {
                Text(
                    text = "Equipment: ${it.duration} minutes"
                )
                Spacer(Modifier.height(8.dp))
            }


            Spacer(Modifier.height(8.dp))
        }
        // TODO: Make this a loading indicator
        ?: Text("Loading Workout...")


    }

}