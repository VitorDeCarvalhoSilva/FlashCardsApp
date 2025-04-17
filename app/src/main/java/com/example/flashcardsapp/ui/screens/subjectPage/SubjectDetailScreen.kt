package com.example.flashcardsapp.ui.screens.subjectDetail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flashcardsapp.ui.components.HeaderButtons
import com.example.flashcardsapp.ui.components.Title
import com.example.flashcardsapp.ui.screens.subjectPage.ExerciseCard
import com.example.flashcardsapp.ui.viewmodels.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SubjectDetailScreen(
    subjectId: Int,
    viewModel: AppViewModel,
    onBackClick: () -> Unit
) {
    val subject = viewModel.subjects.find { it.id == subjectId.toString() } ?: return
    val exercises = viewModel.getExercisesForSubject(subjectId)

    Scaffold {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 40.dp)
        ) {
            item {
                HeaderButtons(
                    onBackClick = onBackClick,
                    onDeleteClick = { /* deletar assunto */ }
                )
            }

            item {
                Title(
                    text = subject.name,
                    icon = Icons.Default.AddCircle,
                    onIconClick = {
                        viewModel.addExercise(subjectId, "Novo exercício")
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))
            }

            items(exercises, key = { it.id }) { exercise ->
                ExerciseCard(exercise = exercise)
            }
        }
    }
}
