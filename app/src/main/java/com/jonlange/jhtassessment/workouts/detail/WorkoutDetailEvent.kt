package com.jonlange.jhtassessment.workouts.detail

import com.jonlange.data.model.WorkoutDifficulty

sealed class WorkoutDetailEvent {
    data class NameChanged(val newName: String) : WorkoutDetailEvent()
    data class EquipmentChanged(val newEquipment: String) : WorkoutDetailEvent()
    data class DurationChanged(val newDuration: String) : WorkoutDetailEvent()
    data class DifficultyChanged(val newDifficulty: WorkoutDifficulty) : WorkoutDetailEvent()
    object Save: WorkoutDetailEvent()
    object Cancel: WorkoutDetailEvent()
}