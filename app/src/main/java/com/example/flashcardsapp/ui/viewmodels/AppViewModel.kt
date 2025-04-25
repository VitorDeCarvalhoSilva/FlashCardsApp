package com.example.flashcardsapp.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.flashcardsapp.entities.Location
import com.example.flashcardsapp.entities.Subject
import com.example.models.Flashcard
import java.util.UUID

class AppViewModel : ViewModel() {

    // Lista de assuntos (mockada)
    val subjects = mutableStateListOf(
        Subject("1", "Português"),
        Subject("2", "História")
    )

    // Lista de exercícios (mockada)


    // Adiciona novo assunto
    fun addSubject(name: String) {
        val id = (subjects.size + 1).toString()
        subjects.add(Subject(id, name))
    }

    // Remove assunto e os exercícios associados
    fun removeSubject(subject: Subject) {
        subjects.remove(subject)
        //Remover todos os exercicios do assunto
    }

    // Adiciona exercício a um assunto específico
    fun addExercise(name: String, subjectId: Int) {

    }

    // Retorna todos os exercícios de um assunto
    fun getExercisesForSubject(subjectId: Int): List<Flashcard> {
        return emptyList()
    }

    val locations = mutableStateListOf(
        Location("1", "Quarto"),
        Location("2", "Biblioteca"),
        Location("3", "Ônibus")
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

}
