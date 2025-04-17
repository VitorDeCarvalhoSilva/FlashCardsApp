package com.example.flashcardsapp.ui.screens.homePage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashcardsapp.ui.components.RoundedOutlinedButton
import com.example.flashcardsapp.ui.components.Title

@Composable
fun AddSubjectOverlay(
    isOpen: MutableState<Boolean>,
    onAddSubject: (String) -> Unit,
){

    val newSubjectName = remember { mutableStateOf("") }
    if (isOpen.value) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f) // ocupa todo o espaço livre
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.25f))
                    .clickable { isOpen.value = false }

            )
            Box(
                modifier = Modifier
                    .height(420.dp)
                    .background(Color.White)
            ){
                Column(
                    modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 40.dp)
                ) {
                    Title("Novo Assunto",)
                    Spacer(
                        modifier = Modifier.height(40.dp)
                    )
                    OutlinedTextField(
                        value = newSubjectName.value,
                        onValueChange = { newSubjectName.value = it },
                        label = { Text("Nome do Assunto") },
                        textStyle = TextStyle(color = Color.Black, fontSize = 20.sp),
                        maxLines = 1,
                        shape = RoundedCornerShape(20.dp), // <- Arredondamento da borda
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            Color.Black,
                        )
                    )

                    Spacer(
                        modifier = Modifier.height(40.dp)
                    )
                    RoundedOutlinedButton(
                        text = "Adicionar",
                        onClick = {
                            println("Clicou no botão Adicionar")
                            val name = newSubjectName.value.trim()
                            if (name.isNotEmpty()) {
                                onAddSubject(name)
                                isOpen.value = false
                                newSubjectName.value = ""
                            }
                        }
                    )

                }
            }
        }
    }


}


