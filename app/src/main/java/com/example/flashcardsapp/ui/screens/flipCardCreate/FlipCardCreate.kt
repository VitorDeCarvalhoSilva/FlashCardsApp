@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.flashcardsapp.ui.screens.flipCardCreate


import PoppinsSemiBold
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashcardsapp.ui.components.HeaderButtons
import com.example.flashcardsapp.ui.components.RoundedOutlinedButton
import com.example.flashcardsapp.ui.components.Title
import com.example.models.BasicFlashcard

@Composable
fun FlipCardCreateScreen(
    userId: Int,
    subjectId: Int,
    onBackClick: () -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var front by remember { mutableStateOf("") }
    var back by remember { mutableStateOf("") }

    println(
        "Objeto de Inserção\n\n"
                + "Subject ID: ${subjectId}\n\n"
                + "User ID: ${userId}\n\n"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp, vertical = 60.dp)
    ) {
        item {
            HeaderButtons(onBackClick = onBackClick)

            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Title(text = "Novo Exercicio", subtitle = "Flip")

            Spacer(modifier = Modifier.height(40.dp))
        }


        item {
            Text(
                text = "Pergunta",
                fontSize = 24.sp,
                fontFamily = PoppinsSemiBold,
                color = Color(0xFF505050),
                fontWeight = FontWeight.SemiBold
            )
            OutlinedTextField(
                value = front,
                onValueChange = { front = it },
                placeholder = { Text("Qual a parte da frente do seu card?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF034B36),
                    unfocusedBorderColor = Color(0xFF034B36)
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

        }
        item {
            Text(
                text = "Resposta",
                fontSize = 24.sp,
                fontFamily = PoppinsSemiBold,
                color = Color(0xFF505050),
                fontWeight = FontWeight.SemiBold
            )
            OutlinedTextField(
                value = back,
                onValueChange = { back = it },
                placeholder = { Text("Qual a parte de trás do seu card?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF034B36),
                    unfocusedBorderColor = Color(0xFF034B36)
                )
            )

            Spacer(modifier = Modifier.height(80.dp))
        }
        item {
            RoundedOutlinedButton(
                text = "Adicionar",
                onClick = {
                    println(
                        "Objeto de inserção: \n\n"
                        + "userId: ${userId}\n"
                        + "subjectId: ${subjectId}\n"
                        + "front: ${front}\n"
                        + "back: ${back}\n"
                    )
                    val basicFlashcard = BasicFlashcard(
                        userId = userId!!,
                        subjectId = subjectId,
                        question = front,
                        answer = back
                    )
                    //Ai salva isso na API
                }
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview
@Composable
fun FlipCardCreateScreenPreview() {
    FlipCardCreateScreen(
        userId = 1,
        subjectId = 1,
        onBackClick = {}
        )
}