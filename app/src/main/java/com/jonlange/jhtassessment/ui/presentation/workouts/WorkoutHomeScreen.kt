package com.jonlange.jhtassessment.ui.presentation.workouts

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.jonlange.jhtassessment.ui.theme.JohnsonAssessmentTheme

@Composable
fun WorkoutHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: WorkoutHomeScreenViewModel = hiltViewModel()
) {
    Greeting(
        name = "Android",
        modifier = modifier
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JohnsonAssessmentTheme {
        Greeting("Android")
    }
}