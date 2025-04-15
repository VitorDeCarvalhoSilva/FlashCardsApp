package com.example.flashcardsapp.ui.screens.homePage

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcardsapp.R
import com.example.flashcardsapp.entities.Subject
import com.example.flashcardsapp.ui.components.MenuOverlay
import com.example.flashcardsapp.ui.components.Title
import com.example.flashcardsapp.ui.viewmodels.AssuntosViewModel



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AssuntosScreen(
    onNavigateToSubject: (Subject) -> Unit,
    viewModel: AssuntosViewModel = viewModel()
) {
    val showAddDialog = remember { mutableStateOf(false) }
    val isAddSubjectOpen = remember { mutableStateOf(false) }
    val isMenuOpen = remember { mutableStateOf(false) }

    Scaffold {
        LazyColumn(modifier = Modifier.padding(horizontal = 40.dp)) {
            item {
                MenuButton(onClick = { isMenuOpen.value = true })
            }

            item {
                Title(
                    text = "Assuntos",
                    subtitle = when {
                        viewModel.locations.isEmpty() -> "Nenhuma localização criada"
                        viewModel.selectedLocation.value == null -> "Nenhuma localização selecionada"
                        else -> viewModel.selectedLocation.value!!.name
                    },
                    icon = Icons.Default.AddCircle,
                    onIconClick = { isAddSubjectOpen.value = true }
                )
            }

            item {
                Spacer(modifier = Modifier.height(60.dp))
            }

            if (viewModel.subjects.isEmpty()) {
                item {
                    Text(
                        text = "Ainda não há assuntos adicionados",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        fontSize = 18.sp
                    )
                }
            } else {
                items(viewModel.subjects) { subject ->
                    Column {
                        SubjectCard(
                            subject = subject,
                            onButtonClick = { onNavigateToSubject(subject) }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }

    // Menu lateral
    MenuOverlay(
        isOpen = isMenuOpen,
        locations = viewModel.locations,
        selectedLocation = viewModel.selectedLocation.value,
        onSelectLocation = { viewModel.selectLocation(it) },
        onAddLocationClick = { showAddDialog.value = true },
        onAddLocation = { viewModel.addLocation(it) },
        onRemoveLocation = { viewModel.removeLocation(it) }
    )

    // Adição de assunto
    if (isAddSubjectOpen.value) {
        AddSubjectOverlay(
            isOpen = isAddSubjectOpen,
            onAddSubject = { viewModel.addSubject(it) }
        )
    }
}  