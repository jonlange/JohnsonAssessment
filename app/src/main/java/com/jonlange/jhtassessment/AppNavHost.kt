package com.jonlange.jhtassessment

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.jonlange.jhtassessment.workouts.navigation.WorkoutsNavHost

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    WorkoutsNavHost(navController = navController)
}