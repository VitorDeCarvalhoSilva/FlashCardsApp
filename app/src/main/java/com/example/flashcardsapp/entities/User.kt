package com.example.flashcardsapp.entities

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val token : String
)