package com.example.flashcardsapp.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.flashcardsapp.entities.Location
import java.util.*

class LocationsViewModel : ViewModel() {
    val locations = mutableStateListOf<Location>()
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
