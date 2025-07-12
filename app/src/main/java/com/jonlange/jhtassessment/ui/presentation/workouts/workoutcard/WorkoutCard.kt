package com.jonlange.jhtassessment.ui.presentation.workouts.workoutcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jonlange.core.model.Workout
import kotlinx.collections.immutable.ImmutableList

@Composable
fun WorkoutCards(
   workouts: ImmutableList<Workout>,
   modifier: Modifier = Modifier
) {
    LazyColumn(contentPadding = PaddingValues(10.dp)) {
        itemsIndexed(workouts) { index, workout ->
            WorkoutCard(
                workout
            )

            if (index != workouts.lastIndex) Spacer(Modifier.size(10.dp))
        }
    }
}

@Composable
private fun WorkoutCard(
    workout: Workout,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        // Workout Name
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp).fillMaxSize()
        ) {
            Text(
                text = workout.name,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}