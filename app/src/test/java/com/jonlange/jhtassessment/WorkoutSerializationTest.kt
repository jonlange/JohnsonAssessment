package com.jonlange.jhtassessment

import com.jonlange.data.model.Workout
import com.jonlange.data.model.WorkoutDifficulty
import junit.framework.TestCase.assertEquals
import kotlinx.serialization.json.Json
import org.junit.Test

class WorkoutSerializationTest {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val workoutsJson = """
        [
          {
            "name": "Cardio Workout",
            "id": "589de45cc3299e1600b2f57d",
            "equipment": "Treadmill",
            "duration": 30,
            "difficulty": "Intermediate"
          },
          {
            "name": "Cardio Burn",
            "id": "5f720bbea9914f000fd6f9da",
            "equipment": "Bike",
            "duration": 20,
            "difficulty": "Beginner"
          },
          {
            "name": "Strength Builder",
            "id": "5f720bbea9914f000fd6f9d9",
            "equipment": "Chest Press",
            "duration": 45,
            "difficulty": "Advanced"
          },
          {
            "name": "Yoga Flow",
            "id": "5f720bbea9914f000fd6f9dc",
            "duration": 25
          },
          {
            "name": "Push Ups",
            "id": "5f720bbea9914f000fd6f9db",
            "duration": 12
          },
          {
            "name": "Endurance strength - workout",
            "id": "5f720bbea9914f000fd6f9d8",
            "equipment": "Biceps Curl",
            "duration": 27,
            "difficulty": "Advanced"
          }
        ]
    """.trimIndent()

    @Test
    fun `deserialize list of Workouts from JSON`() {
        val workouts = json.decodeFromString<List<Workout>>(workoutsJson)

        assertEquals(6, workouts.size)

        val first = workouts[0]
        assertEquals("589de45cc3299e1600b2f57d", first.id)
        assertEquals("Cardio Workout", first.name)
        assertEquals("Treadmill", first.equipment)
        assertEquals(30, first.duration)
        assertEquals(WorkoutDifficulty.Intermediate, first.difficulty)

        val yogaFlow = workouts[3]
        assertEquals("Yoga Flow", yogaFlow.name)
        assertEquals("", yogaFlow.equipment) // Default
        assertEquals(WorkoutDifficulty.Unknown, yogaFlow.difficulty) // Default
    }

    @Test
    fun `serialize Workout to JSON`() {
        val workout = Workout(
            id = "5f720bbea9914f000fd6f9da",
            name = "Cardio Burn",
            equipment = "Bike",
            duration = 20,
            difficulty = WorkoutDifficulty.Beginner
        )

        val jsonString = json.encodeToString(workout)

        val expectedJson = """
            {
                "id": "5f720bbea9914f000fd6f9da",
                "name": "Cardio Burn",
                "equipment": "Bike",
                "duration": 20,
                "difficulty": "Beginner"
            }
        """.trimIndent().replace("\\s".toRegex(), "")

        assertEquals(expectedJson, jsonString.replace("\\s".toRegex(), ""))
    }

    @Test
    fun `deserialize Workout with missing optional fields`() {
        val jsonString = """
            {
                "id": "5f720bbea9914f000fd6f9db",
                "name": "Push Ups",
                "duration": 12
            }
        """.trimIndent()

        val workout = json.decodeFromString<Workout>(jsonString)

        assertEquals("5f720bbea9914f000fd6f9db", workout.id)
        assertEquals("Push Ups", workout.name)
        assertEquals("", workout.equipment) // Default
        assertEquals(12, workout.duration)
        assertEquals(WorkoutDifficulty.Unknown, workout.difficulty) // Default
    }
}