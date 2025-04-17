package com.example.flashcardsapp.ui.screens.subjectDetail

import PoppinsRegular
import PoppinsSemiBold
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashcardsapp.ui.components.HeaderButtons
import com.example.flashcardsapp.ui.components.Title
import com.example.flashcardsapp.ui.screens.subjectPage.ExerciseCard
import com.example.flashcardsapp.ui.viewmodels.AppViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SubjectDetailScreen(
    subjectId: Int,
    viewModel: AppViewModel,
    onBackClick: () -> Unit,
    onNavigateToCreateExercise: (Int) -> Unit
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

                )
            }

            item {
                Title(
                    text = subject.name,
                    icon = Icons.Default.AddCircle,
                    onIconClick = {
                        onNavigateToCreateExercise(subjectId)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
            if(viewModel.getExercisesForSubject(subjectId).isEmpty()){
                item {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                    ) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )
                        Text(
                            text = "  ",
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 20.sp,
                            fontFamily = PoppinsSemiBold,
                            color = Color(0xFF595B5A),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Adicione seu primeiro exercício clickando no '+'",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            fontSize = 20.sp,
                            fontFamily = PoppinsRegular,
                            color = Color(0xFF595B5A),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            items(exercises, key = { it.id }) { exercise ->
                ExerciseCard(exercise = exercise)
            }
        }
    }
}
