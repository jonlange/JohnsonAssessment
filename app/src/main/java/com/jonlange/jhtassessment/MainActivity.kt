package com.jonlange.jhtassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jonlange.data.repository.LocalWorkoutRepo
import com.jonlange.jhtassessment.workouts.WorkoutHomeScreen
import com.jonlange.jhtassessment.ui.theme.JohnsonAssessmentTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Initializing this here so the repo is all setup before we need to access it
    @Inject
    lateinit var localWorkoutRepo: LocalWorkoutRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JohnsonAssessmentTheme {
                AppNavHost()
            }
        }
    }
}

