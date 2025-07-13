package com.jonlange.jhtassessment.workouts.workoutcard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jonlange.data.model.Workout
import com.jonlange.data.model.WorkoutDifficulty
import kotlinx.collections.immutable.ImmutableList

@Composable
fun WorkoutCards(
   workouts: ImmutableList<Workout>,
   onWorkoutSelected: (String) -> Unit,
   modifier: Modifier = Modifier
) {
    LazyColumn(contentPadding = PaddingValues(10.dp)) {
        itemsIndexed(workouts) { index, workout ->
            WorkoutCard(
                workout = workout,
                onClick = onWorkoutSelected
            )

            if (index != workouts.lastIndex) Spacer(Modifier.size(10.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WorkoutCard(
    workout: Workout,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {

    val difficultyLabel = stringResource(workout.difficulty.labelRes)

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(workout.id)
            }
    ) {
        // Workout Name
        Column() {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(workout.difficulty.color)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Icon (
                    imageVector = workout.difficulty.icon,
                    contentDescription = difficultyLabel,
                    tint = Color.Black
                )
                Text (
                    text = difficultyLabel,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // TODO: Update this with AutoSizeText
            Text(
                text = workout.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text (
                    text = "${workout.duration} Minutes",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text (
                    text = if (workout.equipment.isEmpty()) "No Equipment Required" else "Equipment Required"
                )
            }
        }
    }
}

@Preview
@Composable
private fun WorkoutCardPreview() {
    val sampleWorkout = Workout(
        name ="Endurance strength - workout",
        id = "5f720bbea9914f000fd6f9d8",
        equipment = "Biceps Curl",
        duration = 27,
        difficulty = WorkoutDifficulty.Advanced)

    WorkoutCard(
        workout = sampleWorkout,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
}