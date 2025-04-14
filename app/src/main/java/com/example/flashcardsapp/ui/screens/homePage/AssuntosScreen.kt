package com.example.flashcardsapp.ui.screens.homePage

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.flashcardsapp.R
import com.example.flashcardsapp.ui.components.MenuOverlay
import com.example.flashcardsapp.ui.components.Title
import java.util.UUID

// ─────────────────────────────────────────────────────────────
// 📄 AssuntosScreen.kt
// Esta tela representa a página inicial de assuntos disponíveis.
// Exibe um título com botão para adicionar novo assunto e uma
// lista de cards representando os assuntos disponíveis,
// simulando dados retornados por uma API.
// Cada card pode ser clicado para exibir flashcards do assunto.
// ─────────────────────────────────────────────────────────────

// Classe temporária para simular um objeto retornado pela API
class Subject(nome: String) {
    val nome = nome
}

data class Location(val id: String,val name: String)

val PoppinsBold = FontFamily(Font(R.font.poppins_bold)) // Fonte personalizada
val PoppinsSemiBold = FontFamily(Font(R.font.poppins_semibold))
val PoppinsRegular = FontFamily(Font(R.font.poppins_regular))


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AssuntosScreen(

) {
    val isMenuOpen = remember { mutableStateOf(false) } // Estado para controle do menu
    val locations = remember { mutableStateListOf(
        Location("1", "Quarto"),
        Location("2", "Biblioteca"),
        Location("3", "Ônibus")
    ) }

    Scaffold() {

        //lista de objetos simulados
        val subjects = listOf(
            Subject("Matemática"),
            Subject("Português"),
            Subject("História")
        )

        LazyColumn (modifier = Modifier.padding(horizontal = 40.dp)){
            // Botão de menu com ação para abrir o menu lateral
            item {
                MenuButton(onClick = {
                    isMenuOpen.value = true
                })
            }

            // Título da tela + botão de adicionar assunto
            item {
                Title(
                    text = "Assuntos",
                    icon = Icons.Default.AddCircle,
                    onIconClick = {
                        println("Clicou no ícone adicionar assunto")
                    }
                )
            }

            // Espaço entre o título e os cards
            item {
                Spacer(modifier = Modifier.height(60.dp))
            }

            // Lista de cards de assuntos
            for (subject in subjects) {
                item {
                    SubjectCard(
                        subject = subject,
                        onButtonClick = {
                            println("Clicou no botão ${subject.nome}")
                        }
                    )
                }

                // Espaçamento entre cards
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
        // Overlay do menu lateral
        MenuOverlay(
            isOpen = isMenuOpen,
            locations = locations,
            onAddLocation = { name ->
                val newId = UUID.randomUUID().toString()
                locations.add(Location(newId, name))
            },
            onRemoveLocation = { location ->
                locations.remove(location)
            },
        )
    }
}
