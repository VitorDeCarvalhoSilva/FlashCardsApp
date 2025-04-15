package com.example.flashcardsapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashcardsapp.entities.Exercise
import com.example.flashcardsapp.entities.Subject
import com.example.flashcardsapp.ui.screens.homePage.AssuntosScreen
import com.example.flashcardsapp.ui.screens.subjectDetail.SubjectDetailScreen

@Composable
fun FlashCardsApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "assuntos") {

        composable("assuntos") {
            AssuntosScreen(
                onNavigateToSubject = { subject ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("subject", subject)

                    navController.navigate("subject_detail")
                }
            )
        }

        composable("subject_detail") { backStackEntry ->
            val subject = remember {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<Subject>("subject")
            }

            if (subject != null) {
                SubjectDetailScreen(
                    subject = subject,
                    onBackClick = { navController.popBackStack() }
                )
            } else {
                // DEBUG
                Text(
                    text = "Carregando assunto...",
                    color = Color.Gray,
                    modifier = Modifier.padding(40.dp)
                )
            }
        }


    }
}
