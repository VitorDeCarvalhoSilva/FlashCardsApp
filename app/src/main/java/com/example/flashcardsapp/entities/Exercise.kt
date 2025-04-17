package com.example.flashcardsapp.entities

import java.io.Serializable
data class Exercise(
    val id: Int,
    val name: String,
    val subjectId: Int // novo campo
):Serializable