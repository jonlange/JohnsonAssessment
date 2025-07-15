package com.jonlange.jhtassessment.workouts.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jonlange.data.model.Workout
import com.jonlange.data.model.WorkoutDifficulty
import com.jonlange.jhtassessment.ui.theme.OffWhite
import com.jonlange.jhtassessment.ui.theme.Purple80
import com.jonlange.jhtassessment.ui.theme.PurpleGrey40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutDetailScreen(
    viewModel: WorkoutDetailViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = uiState.editedWorkout?.difficulty?.color ?: WorkoutDifficulty.Unknown.color,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Workout Details")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    // If we are showing editing content, show Save and Cancel
                    if (uiState.isEditing) {
                        IconButton(
                            onClick = {
                                viewModel.onEvent(WorkoutDetailEvent.Save)
                            }
                        ) {
                            Icon(Icons.Default.Check, contentDescription = "Save")
                        }
                        IconButton(
                            onClick = {
                                viewModel.onEvent(WorkoutDetailEvent.Cancel)
                            }
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Cancel")
                        }
                    }
                    // If we are in view only, show Edit
                    else {
                        IconButton(
                            onClick = {
                                viewModel.setEditing(true)
                            }
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->

        val workout = uiState.editedWorkout

        // This should never actually happen, but we want to return early if we are still loading.
        if (workout == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (uiState.isEditing) {
                // Workout Editing Screen
                WorkoutEditContent(
                    workout = workout,
                    onEvent = {
                        // Let viewmodel handle events
                        viewModel.onEvent(it)
                    }
                )
            } else {
                // Workout Detail Screen
                WorkoutDetail(
                    workout = workout
                )
            }
        }
    }

    if (uiState.isSaving) {
        // Show progress indicator
    }

    // TODO: Handle any error messages emitted by the UI state
}

@Composable
fun WorkoutDetail(
    workout: Workout,
) {
    Text(
        text = workout.name.ifBlank { "Unnamed Workout" },
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(16.dp))

    WorkoutDetailItem("Difficulty", workout.difficulty.name)
    WorkoutDetailItem("Duration", "${workout.duration} minutes")
    WorkoutDetailItem("Equipment", workout.equipment.ifBlank { "None" })

}

@Composable
fun WorkoutDetailItem(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
    }

}