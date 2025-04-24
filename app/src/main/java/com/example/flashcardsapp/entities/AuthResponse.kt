package com.example.flashcardsapp.entities

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String,
    val username: String,
    val id: Int
)
