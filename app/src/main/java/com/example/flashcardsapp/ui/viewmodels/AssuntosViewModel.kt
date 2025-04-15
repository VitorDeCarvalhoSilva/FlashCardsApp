package com.example.flashcardsapp.ui.viewmodels


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.flashcardsapp.entities.Location
import com.example.flashcardsapp.entities.Subject
import java.util.*

class AssuntosViewModel : ViewModel() {
    val subjects = mutableStateListOf<Subject>()
    val locations = mutableStateListOf<Location>(
        Location("1", "Localização 1"),
        Location("2", "Localização 2"),
        Location("3", "Localização 3")
    )

    val selectedLocation = mutableStateOf<Location?>(null)

    fun addSubject(name: String) {
        val id = UUID.randomUUID().toString()
        subjects.add(Subject(id, name))
    }

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
