package com.jonlange.jhtassessment

import androidx.lifecycle.SavedStateHandle
import com.jonlange.data.model.Workout
import com.jonlange.data.model.WorkoutDifficulty
import com.jonlange.jhtassessment.di.FakeLocalWorkoutRepo
import com.jonlange.jhtassessment.workouts.detail.WorkoutDetailEvent
import com.jonlange.jhtassessment.workouts.detail.WorkoutDetailViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
class WorkoutDetailViewModelHiltTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private val dispatcher = StandardTestDispatcher()

    @Inject
    lateinit var fakeRepo: FakeLocalWorkoutRepo

    private lateinit var viewModel: WorkoutDetailViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        Dispatchers.setMain(dispatcher)

        fakeRepo.createWorkouts(
            listOf(
                Workout(
                    id = "589de45cc3299e1600b2f57d",
                    name = "Cardio Workout",
                    equipment = "Treadmill",
                    duration = 30,
                    difficulty = WorkoutDifficulty.Intermediate
                )
            )
        )

        val savedStateHandle = SavedStateHandle(mapOf("workoutId" to "589de45cc3299e1600b2f57d"))
        viewModel = WorkoutDetailViewModel(fakeRepo, savedStateHandle)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testInitialState() = runTest {
        // Advance the dispatcher to let init {} run
        dispatcher.scheduler.advanceUntilIdle()

        // Get the latest uiState value
        val uiState = viewModel.uiState.value

        assertEquals("Cardio Workout", uiState.workout?.name)
        assertEquals("Treadmill", uiState.workout?.equipment)
        assertEquals(30, uiState.workout?.duration)
        assertEquals(WorkoutDifficulty.Intermediate, uiState.workout?.difficulty)
    }

    @Test
    fun testSave() = runTest {
        dispatcher.scheduler.advanceUntilIdle()

        viewModel.onEvent(WorkoutDetailEvent.NameChanged("Updated Name"))
        viewModel.onEvent(WorkoutDetailEvent.Save)

        dispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals("Updated Name", uiState.workout?.name)

        // Verify repo data also updated
        val updatedWorkout = fakeRepo.workouts.value.first()
        assertEquals("Updated Name", updatedWorkout.name)
    }
}