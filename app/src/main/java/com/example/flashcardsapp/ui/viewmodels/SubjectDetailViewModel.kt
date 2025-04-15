package com.example.flashcardsapp.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.flashcardsapp.entities.Exercise
import java.util.*

class SubjectDetailViewModel : ViewModel() {
    val exercises = mutableStateListOf<Exercise>()

    fun addExercise(nome: String) {
        val id = UUID.randomUUID().toString()
        exercises.add(Exercise(id, nome))
    }

    fun removeExercise(exercise: Exercise) {
        exercises.remove(exercise)
    }

    fun reset() {
        exercises.clear()
    }
}
