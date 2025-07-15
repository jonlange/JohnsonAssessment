package com.jonlange.jhtassessment.workouts.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonlange.data.model.Workout
import com.jonlange.data.model.WorkoutDifficulty
import com.jonlange.data.repository.LocalWorkoutRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailViewModel @Inject constructor(
    private val localWorkoutRepo: LocalWorkoutRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private companion object {
        const val TAG = "WorkoutDetailViewModel"
    }

    private val workoutId: String = checkNotNull(savedStateHandle["workoutId"]) {
        "missing workoutId"
    }

    private val _uiState = MutableStateFlow(WorkoutDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        Log.d(TAG, "********* workoutId: $workoutId")

        viewModelScope.launch {
            localWorkoutRepo.workouts.collect { workouts ->
                val workout = workouts.firstOrNull { it.id == workoutId}
                Log.d(TAG, "Workout Found: $workout")

                _uiState.update {
                    it.copy(
                        workout = workout,
                        editedWorkout = workout
                    )
                }

            }
        }
    }

    fun saveWorkout() {
        val updatedWorkout = _uiState.value.editedWorkout ?: return

        _uiState.update { it.copy(isSaving = true) }

        viewModelScope.launch {
            // TODO: Add support for this to the repo
            localWorkoutRepo.updateWorkout(updatedWorkout)
            _uiState.update {
                it.copy(
                    workout = updatedWorkout,
                    editedWorkout = updatedWorkout,
                    isSaving = false,
                    isEditing = false
                )
            }
        }
    }

    // Reset editedWorkout back to original
    fun cancelEditing() {
        _uiState.update { current ->
            current.copy(
                editedWorkout = current.workout,
                isEditing = false
            )
        }
    }

    // Toggles editing
    fun setEditing(isEditing: Boolean) {
        _uiState.update { it.copy(isEditing = isEditing)}
    }

    fun onEvent(event: WorkoutDetailEvent) {
        when (event) {
            is WorkoutDetailEvent.NameChanged -> {
                _uiState.update { current ->
                    current.copy(
                        editedWorkout = current.editedWorkout?.copy(name = event.newName)
                    )
                }
            }
            is WorkoutDetailEvent.EquipmentChanged -> {
                _uiState.update { current ->
                    current.copy(
                        editedWorkout = current.editedWorkout?.copy(equipment = event.newEquipment)
                    )
                }
            }
            is WorkoutDetailEvent.DurationChanged -> {
                val duration = event.newDuration.toIntOrNull() ?: 0
                _uiState.update { current ->
                    current.copy(
                        editedWorkout = current.editedWorkout?.copy(duration = duration)
                    )
                }
            }
            is WorkoutDetailEvent.DifficultyChanged -> {
                _uiState.update { current ->
                    current.copy(
                        editedWorkout = current.editedWorkout?.copy(difficulty = event.newDifficulty)
                    )
                }
            }
            WorkoutDetailEvent.Save -> {
                saveWorkout()
            }
            WorkoutDetailEvent.Cancel -> {
                cancelEditing()
            }
        }
    }

}