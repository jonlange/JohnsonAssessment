package com.jonlange.jhtassessment.workouts.detail

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jonlange.data.model.Workout
import com.jonlange.data.model.WorkoutDifficulty

@Composable
fun WorkoutEditContent(
    workout: Workout,
    onEvent: (WorkoutDetailEvent) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = workout.name,
            onValueChange = { onEvent(WorkoutDetailEvent.NameChanged(it)) },
            label = { Text("Workout Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = workout.equipment,
            onValueChange = { onEvent(WorkoutDetailEvent.EquipmentChanged(it)) },
            label = { Text("Equipment") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = workout.duration.toString(),
            onValueChange = { onEvent(WorkoutDetailEvent.DurationChanged(it)) },
            label = { Text("Duration (minutes)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        DifficultyDropdown(
            selected = workout.difficulty,
            onSelected = { onEvent(WorkoutDetailEvent.DifficultyChanged(it)) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DifficultyDropdown(
    selected: WorkoutDifficulty,
    onSelected: (WorkoutDifficulty) -> Unit
) {
    val difficulties = WorkoutDifficulty.entries.toTypedArray()

    Column {
        Text(
            text = "Difficulty",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            difficulties.forEach { difficulty ->
                FilterChip(
                    selected = selected == difficulty,
                    onClick = { onSelected(difficulty) },
                    label = {
                        Text(difficulty.name)
                    },
                    modifier = Modifier
                        .height(40.dp)
                )
            }
        }
    }
}