package com.jonlange.jhtassessment.ui.presentation.workouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jonlange.jhtassessment.ui.presentation.workouts.workoutcard.WorkoutCards
import com.jonlange.jhtassessment.ui.theme.Purple80
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineScope

@Composable
fun WorkoutHomeScreen(
    modifier: Modifier = Modifier,
    // TODO: Add navigator
//    snackbarScope: CoroutineScope = rememberCoroutineScope(),
//    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    viewModel: WorkoutHomeScreenViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.reset()
    }

    WorkoutHomeScreen(
        uiState,
        modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutHomeScreen(
    uiState: WorkoutHomeScreenUiState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Purple80,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Allow user to add workout
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
                        workouts = uiState.workouts.toImmutableList()
                    )
                }
            }
        }
    }
}