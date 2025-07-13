package com.jonlange.jhtassessment.workouts.navigation

sealed class WorkoutRoutes(val route: String) {

    object WorkoutHomeScreen : WorkoutRoutes("workouts/home")
    object WorkoutDetails : WorkoutRoutes("workouts/details/{workoutId}") {
        fun createRoute(workoutId: String): String = "workouts/details/$workoutId"
    }

}