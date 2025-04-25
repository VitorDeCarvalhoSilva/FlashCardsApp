@file:Suppress("NAME_SHADOWING")

package com.example.flashcardsapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flashcardsapp.login.LoginScreen
import com.example.flashcardsapp.ui.screens.createExercise.CreateExerciseScreen
import com.example.flashcardsapp.ui.screens.flipCardCreate.FlipCardCreateScreen
import com.example.flashcardsapp.ui.screens.homePage.AssuntosScreen
import com.example.flashcardsapp.ui.screens.register.RegisterScreen
import com.example.flashcardsapp.ui.screens.subjectDetail.SubjectDetailScreen
import com.example.flashcardsapp.ui.viewmodels.AppViewModel
import com.example.flashcardsapp.ui.viewmodels.AuthViewModel

@Composable
fun FlashCardsApp() {
    val navController = rememberNavController()
    val appViewModel: AppViewModel = viewModel()
    val authViewModel: AuthViewModel = viewModel() // <- MOVIDO PARA CIMA

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(
                authViewModel = authViewModel, // <- PASSE AQUI
                onLoginSuccess = {
                    navController.navigate("assuntos") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterScreen(
                authViewModel = authViewModel, // <- PASSE AQUI TAMBÉM
                onRegisterSuccess = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate("login")
                }
            )
        }

        composable("assuntos") {
            AssuntosScreen(
                viewModel = appViewModel,
                authViewModel = authViewModel, // <- E AQUI
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
            val userId = authViewModel.loggedUser.value?.id ?: return@composable
            SubjectDetailScreen(
                subjectId = subjectId,
                userId = userId,
                viewModel = appViewModel,
                onBackClick = { navController.popBackStack() },
                onNavigateToCreateExercise = { subjectId ->
                    navController.navigate("create_exercise/$subjectId")
                }
            )
        }

        composable(
            route = "create_exercise/{subjectId}",
            arguments = listOf(navArgument("subjectId") { type = NavType.IntType })
        ) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getInt("subjectId") ?: return@composable
            val userId = authViewModel.loggedUser.value?.id ?: return@composable
            CreateExerciseScreen(
                subjectId = subjectId,
                viewModel = appViewModel,
                userId = userId,
                onBackClick = { navController.popBackStack() },
                onNavigateToCreateQuizEercise = {},
                onNavigateToFlipCardCreate = {navController.navigate("flip_card_create/$userId/$subjectId")},
                onNavigateToClozeExercise = {},

            )
        }
        composable("flip_card_create/{userId}/{subjectId}") {
            val userId = it.arguments?.getString("userId")
            val subjectId = it.arguments?.getString("subjectId")
            FlipCardCreateScreen(
                userId = userId!!.toInt(),
                subjectId = subjectId!!.toInt(),
                onBackClick = { navController.popBackStack() },

            )
        }
    }
}

