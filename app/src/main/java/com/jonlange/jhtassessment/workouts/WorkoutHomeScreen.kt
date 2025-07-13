package com.jonlange.jhtassessment.workouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jonlange.jhtassessment.workouts.workoutcard.WorkoutCards
import com.jonlange.jhtassessment.ui.theme.Purple80
import kotlinx.collections.immutable.toImmutableList

@Composable
fun WorkoutHomeScreen(
    modifier: Modifier = Modifier,
    onWorkoutSelected: (String) -> Unit,
    viewModel: WorkoutHomeScreenViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.reset()
    }

    WorkoutHomeScreen(
        uiState = uiState,
        modifier = modifier,
        onWorkoutSelected = onWorkoutSelected
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutHomeScreen(
    uiState: WorkoutHomeScreenUiState,
    modifier: Modifier = Modifier,
    onWorkoutSelected: (String) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Purple80,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // TODO: Allow user to add a new workout
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Workout"
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            PullToRefreshBox(
                isRefreshing = uiState.isRefreshing,
                onRefresh = {},
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {

                if (uiState.workouts.isEmpty()) {
                    // Show empty view
                } else {
                    // Create Workouts composable
                    WorkoutCards(
                        workouts = uiState.workouts.toImmutableList(),
                        onWorkoutSelected = onWorkoutSelected
                    )
                }
            }
        }
    }
}