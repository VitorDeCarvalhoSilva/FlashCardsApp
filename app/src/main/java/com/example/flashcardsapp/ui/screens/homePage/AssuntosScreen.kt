@file:Suppress("DEPRECATED_IDENTITY_EQUALS")

package com.example.flashcardsapp.ui.screens.homePage

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashcardsapp.R
import com.example.flashcardsapp.ui.components.MenuOverlay
import com.example.flashcardsapp.ui.components.Title
import java.util.UUID
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign


// ─────────────────────────────────────────────────────────────
// 📄 AssuntosScreen.kt
// Esta tela representa a página inicial de assuntos disponíveis.
// Exibe um título com botão para adicionar novo assunto e uma
// lista de cards representando os assuntos disponíveis,
// simulando dados retornados por uma API.
// Cada card pode ser clicado para exibir flashcards do assunto.
// ─────────────────────────────────────────────────────────────

// Classe temporária para simular um objeto retornado pela API
class Subject(val id: String,nome: String) {
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
    //lista de Assuntos simulados
    val MockedSubjects = remember { mutableStateListOf<Subject>()}

    val isMenuOpen = remember { mutableStateOf(false) } // Estado para controle do menu
    val MockedLocations = remember { mutableStateListOf<Location>(
        Location("1", "Localização 1")
        , Location("2", "Localização 2")
        , Location("3", "Localização 3")
    ) }

    val locations = remember { mutableStateListOf<Location>() }
    val selectedLocation = remember { mutableStateOf<Location?>(null) }

    val showAddDialog = remember { mutableStateOf(false) }
    val newLocationName = remember { mutableStateOf("") }

    val isAddSubjectOpen = remember { mutableStateOf(false) }


    val subtitleText = when {
        MockedLocations.isEmpty() -> "Nenhuma localização criada"
        selectedLocation.value == null -> "Nenhuma localização selecionada"
        else -> selectedLocation.value!!.name
    }

    Scaffold() {
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
                    subtitle = subtitleText,
                    icon = Icons.Default.AddCircle,
                    onIconClick = {
                        isAddSubjectOpen.value = true
                    }
                )
            }

            // Espaço entre o título e os cards
            item {
                Spacer(modifier = Modifier.height(60.dp))
            }


            if (MockedSubjects.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Text(
                                text = "Ainda não há assuntos adicionados :(",
                                fontSize = 24.sp,
                                fontFamily = PoppinsRegular,
                                color = Color(0xFF595B5A),
                                textAlign = TextAlign.Center,
                                lineHeight = 40.sp
                            )
                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )
                            Text(
                                text = "Voce pode adicionar cards pressionando o botão '+'",
                                fontSize = 15.sp,
                                fontFamily = PoppinsRegular,
                                color = Color.Gray,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            } else {
                items(MockedSubjects) { subject ->
                    Column {
                        SubjectCard(
                            subject = subject,
                            onButtonClick = {
                                println("Clicou no botão ")
                            }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }

        }
        // Overlay do menu lateral
        MenuOverlay(
            isOpen = isMenuOpen,
            locations = MockedLocations,
            onAddLocationClick = { showAddDialog.value = true },
            onAddLocation = { name ->
                val newId = UUID.randomUUID().toString()
                MockedLocations.add(Location(newId, name))
            },
            onRemoveLocation = { location ->
                MockedLocations.remove(location)
                if (selectedLocation.value == location) {
                    selectedLocation.value = null
                }
            },
            selectedLocation = selectedLocation.value,
            onSelectLocation = {
                selectedLocation.value = it
                isMenuOpen.value = false
            }
        )
        
        //AddSubjectOverlay
        AddSubjectOverlay(
            isOpen = isAddSubjectOpen,
            onAddSubject = { name ->
                val newId = UUID.randomUUID().toString()
                MockedSubjects.add(Subject(newId, name))
            }
        )


        if (showAddDialog.value) {
            AddLocationAlert(
                showDialog = showAddDialog,
                onConfirm = { name ->
                    val newId = UUID.randomUUID().toString()
                    MockedLocations.add(Location(newId, name))
                    selectedLocation.value = MockedLocations.last()
                }
            )
        }


    }
}

