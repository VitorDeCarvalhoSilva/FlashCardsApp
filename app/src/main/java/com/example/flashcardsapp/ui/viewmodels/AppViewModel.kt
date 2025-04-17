package com.example.flashcardsapp.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.flashcardsapp.entities.Exercise
import com.example.flashcardsapp.entities.Location
import com.example.flashcardsapp.entities.Subject
import java.util.UUID

class AppViewModel : ViewModel() {


    //Subjects and Exercises

    val subjects = mutableStateListOf<Subject>()
    val exercises = mutableStateListOf<Exercise>()
    private var nextId = 1

    fun addSubject(name: String) {
        subjects.add(Subject(nextId++.toString(), name))
    }

    fun getExercisesForSubject(subjectId: Int): List<Exercise> {
        return exercises.filter { it.subjectId == subjectId }
    }

    fun addExercise(subjectId: Int, name: String) {
        exercises.add(Exercise(nextId++, name, subjectId))
    }

    fun removeExercise(exercise: Exercise) {
        exercises.remove(exercise)
    }


    //Location

    val locations = mutableStateListOf<Location>(
        Location("1", "Localização 1"),
        Location("2", "Localização 2"),
        Location("3", "Localização 3")
    )
    val selectedLocation = mutableStateOf<Location?>(null)

    fun addLocation(name: String) {
        val id = UUID.randomUUID().toString()
        locations.add(Location(id, name))
        selectedLocation.value = locations.last()
    }

    fun removeLocation(location: Location) {
        locations.remove(location)
        if (selectedLocation.value == location) {
            selectedLocation.value = null
        }
    }

    fun selectLocation(location: Location) {
        selectedLocation.value = location
    }

    fun removeSubject(subject: Subject) {
        subjects.remove(subject)
        val subjectIdInt = subject.id.toIntOrNull()
        if (subjectIdInt != null) {
            exercises.removeAll { it.subjectId == subjectIdInt }
        }
    }

}
