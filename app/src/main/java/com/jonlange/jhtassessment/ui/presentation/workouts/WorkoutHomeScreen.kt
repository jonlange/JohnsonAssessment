package com.jonlange.jhtassessment.ui.presentation.workouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jonlange.jhtassessment.ui.presentation.workouts.workoutcard.WorkoutCards
import kotlinx.collections.immutable.toImmutableList

@Composable
fun WorkoutHomeScreen(
    modifier: Modifier = Modifier,
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
    Column(
        modifier = modifier.fillMaxSize()
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