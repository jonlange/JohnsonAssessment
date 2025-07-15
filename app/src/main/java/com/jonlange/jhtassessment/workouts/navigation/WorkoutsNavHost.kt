package com.jonlange.jhtassessment.workouts.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jonlange.jhtassessment.workouts.detail.WorkoutDetailScreen
import com.jonlange.jhtassessment.workouts.WorkoutHomeScreen

@Composable
fun WorkoutsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = WorkoutRoutes.WorkoutHomeScreen.route,
        modifier = modifier
    ) {

        // Workout Home Screen
        composable(
            route = WorkoutRoutes.WorkoutHomeScreen.route
        ) {
            WorkoutHomeScreen(
                onWorkoutSelected = { workoutId ->
                    navController.navigate(WorkoutRoutes.WorkoutDetails.createRoute(workoutId))
                }
            )
        }

        // Workout Details Screen
        composable(
            route = WorkoutRoutes.WorkoutDetails.route,
            arguments = listOf(
                navArgument("workoutId") {
                    type = NavType.StringType
                }
            )
        ) { backStack ->
            WorkoutDetailScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

    }

}