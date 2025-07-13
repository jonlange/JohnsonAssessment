package com.jonlange.jhtassessment.workouts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonlange.data.repository.LocalWorkoutRepo
import com.jonlange.jhtassessment.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutHomeScreenViewModel @Inject constructor(
    private val localWorkoutRepo: LocalWorkoutRepo
) : ViewModel() {

    private companion object {
        const val TAG = "WorkoutHomeScreenViewModel"
    }

    private val _uiState = MutableStateFlow(WorkoutHomeScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun reset() {
        _uiState.update { it.copy(isRefreshing = true) }

        viewModelScope.launch {
            // Load the workouts from the list
            _uiState.update {
                it.copy(
                    workouts = localWorkoutRepo.loadWorkoutsFromRaw(R.raw.workouts)
                )
            }
        }

        // I could probably merge this with the above loading, but I think its clearer this way
        _uiState.update { it.copy(isRefreshing = false) }
    }

}