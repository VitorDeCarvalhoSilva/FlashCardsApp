package com.example.flashcardsapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flashcardsapp.ui.screens.homePage.AssuntosScreen
import com.example.flashcardsapp.ui.screens.subjectDetail.SubjectDetailScreen
import com.example.flashcardsapp.ui.viewmodels.AppViewModel

@Composable
fun FlashCardsApp() {
    val navController = rememberNavController()
    val appViewModel: AppViewModel = viewModel()

    NavHost(navController = navController, startDestination = "assuntos") {

        composable("assuntos") {
            AssuntosScreen(
                viewModel = appViewModel,
                onNavigateToSubject = { subject ->
                    navController.navigate("subject_detail/${subject.id}")
                }
            )
        }

        composable(
            route = "subject_detail/{subjectId}",
            arguments = listOf(navArgument("subjectId") { type = NavType.IntType })
        ) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getInt("subjectId") ?: return@composable
            SubjectDetailScreen(
                subjectId = subjectId,
                viewModel = appViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
