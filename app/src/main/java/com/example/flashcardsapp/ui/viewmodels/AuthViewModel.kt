package com.example.flashcardsapp.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardsapp.entities.AuthResponse
import com.example.flashcardsapp.entities.User
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class AuthViewModel : ViewModel() {
    private val baseUrl = "http://10.0.2.2:8080/auth"

    val username = mutableStateOf("")
    val password = mutableStateOf("")
    val isRegistered = mutableStateOf(false)
    val isLoggedIn = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)
    val loggedUser = mutableStateOf<User?>(null)


    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    fun registerUser(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response: HttpResponse = client.post("$baseUrl/register") {
                    contentType(ContentType.Application.Json)
                    setBody(mapOf("username" to username, "password" to password))
                }

                when (response.status) {
                    HttpStatusCode.Created -> {
                        println("✅ Registro bem-sucedido")
                        isRegistered.value = true
                    }
                    HttpStatusCode.Conflict -> {
                        println("❌ Erro: nome de usuário já existe")
                        errorMessage.value = "❌ Nome de usuário já existe"
                    }
                    else -> {
                        println("❌ Erro inesperado: ${response.status}")
                    }
                }
            } catch (e: Exception) {
                println("❌ Exceção no registro: ${e.message}")
            }
        }
    }

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response: HttpResponse = client.post("$baseUrl/login") {
                    contentType(ContentType.Application.Json)
                    setBody(mapOf("username" to username, "password" to password))
                }

                if (response.status == HttpStatusCode.OK) {
                    val responseBody = response.bodyAsText()
                    println("Response Body: $responseBody")
                    val user = Json.decodeFromString<User>(responseBody)
                    loggedUser.value = user
                    isLoggedIn.value = true
                    println("Login bem-sucedido: ${user.username}")
                }
                else {
                    errorMessage.value = "Erro no login: ${response.status}"
                    println("Erro no login: ${response.status}")
                }
            } catch (e: Exception) {
                errorMessage.value = "Erro ao logar: ${e.message}"
                println("Erro ao logar: ${e.message}")
            }
        }
    }

    @Serializable
    data class UserCredentials(
        val username: String,
        val password: String
    )
}
