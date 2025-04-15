package com.example.flashcardsapp.ui.components

import android.view.MenuItem
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flashcardsapp.ui.screens.homePage.AccordionSection
import com.example.flashcardsapp.ui.screens.homePage.Location

// ─────────────────────────────────────────────────────────────
// 📄 MenuOverlay.kt
// Componente de menu lateral com largura fixa de 300.dp,
// cobrindo toda a altura da tela. Possui um fundo escuro
// translúcido fora do menu que fecha o painel ao ser clicado.
// Utiliza o componente Title para o cabeçalho.
// ─────────────────────────────────────────────────────────────
@Composable
fun MenuOverlay(
    isOpen: MutableState<Boolean>,
    locations: List<Location>,
    onAddLocation: (String) -> Unit,
    onRemoveLocation: (Location) -> Unit,
    selectedLocation: Location?,
    onSelectLocation: (Location) -> Unit,
    onAddLocationClick: () -> Unit
) {
    if (isOpen.value) {
        Box(Modifier.fillMaxSize()) {

            // Cinza escuro ao fundo
            Box(
                modifier = Modifier
                    .padding(start = 300.dp)
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.25f))
                    .clickable { isOpen.value = false }
            )

            Column(
                modifier = Modifier
                    .width(300.dp)
                    .fillMaxHeight()
                    .background(Color.White)
                    .padding(horizontal = 40.dp),

            ) {
                Spacer(Modifier.height(60.dp))
                Title("Menu")

                Spacer(Modifier.height(60.dp))

                AccordionSection(
                    title = "Localização",
                    locations = locations,
                    selectedLocation = selectedLocation,
                    onAddClick = onAddLocationClick,
                    onRemove = onRemoveLocation,
                    onSelect = onSelectLocation
                )


                Spacer(Modifier.height(32.dp))


            }
        }
    }
}
