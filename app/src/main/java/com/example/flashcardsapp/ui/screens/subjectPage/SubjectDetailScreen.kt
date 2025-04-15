package com.example.flashcardsapp.ui.screens.subjectDetail

import PoppinsBold
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcardsapp.entities.Subject
import com.example.flashcardsapp.ui.viewmodels.SubjectDetailViewModel




@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SubjectDetailScreen(
    subject: Subject,
    onBackClick: () -> Unit,
    viewModel: SubjectDetailViewModel = viewModel()
) {
    Scaffold {
        Column(modifier = Modifier.padding(32.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }

                IconButton(onClick = { /* deletar assunto */ }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Excluir assunto",
                        tint = Color.Red
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Título do Assunto + botão de adicionar exercício
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = subject.name,
                    fontSize = 32.sp,
                    fontFamily = PoppinsBold,
                    color = Color(0xFF034B36),
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = {
                    viewModel.addExercise("Novo exercício")
                }) {
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Adicionar exercício",
                        tint = Color(0xFF2EBF96)
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Lista de exercícios
            viewModel.exercises.forEach { exercise ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF2EBF96)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = exercise.name,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontFamily = PoppinsBold
                        )
                    }
                }
            }
        }
    }
}