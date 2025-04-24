package com.example.flashcardsapp.login

import PoppinsBold
import PoppinsRegular
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcardsapp.ui.components.Capriola
import com.example.flashcardsapp.ui.components.RoundedOutlinedButton
import com.example.flashcardsapp.ui.viewmodels.AuthViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    authViewModel: AuthViewModel
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String>("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Login",
                fontSize = 36.sp,
                fontFamily = Capriola,
                color = androidx.compose.ui.graphics.Color(0xFF034B36),

                )
        }
        Spacer(modifier = Modifier.height(16.dp))
        if(errorMessage.isNotEmpty()){
            com.example.flashcardsapp.ui.screens.register.PrintErrorMessage(text = errorMessage)
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                fontFamily = PoppinsRegular,
                fontSize = 18.sp,
                color = Color.Black
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF034B36),  // Cor da borda quando está selecionado
                unfocusedBorderColor = Color.Gray,       // Cor da borda quando está desativado
                focusedLabelColor = Color(0xFF034B36),   // Cor da label quando está focado
                cursorColor = Color(0xFF034B36)          // Cor do cursor
            ),
            maxLines = 1

        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                fontFamily = PoppinsBold,
                fontSize = 18.sp,
                color = Color.Black
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF034B36),  // Cor da borda quando está selecionado
                unfocusedBorderColor = Color.Gray,       // Cor da borda quando está desativado
                focusedLabelColor = Color(0xFF034B36),   // Cor da label quando está focado
                cursorColor = Color(0xFF034B36)          // Cor do cursor
            ),
            maxLines = 1
        )
        val viewModel: AuthViewModel = viewModel()
        Spacer(modifier = Modifier.height(16.dp))
        RoundedOutlinedButton(
            text = "Login",
            onClick = {
                if(
                    username.isEmpty() ||
                    password.isEmpty()
                ){
                    errorMessage = "❌   Preencha todos os campos!"
                }else {
                    viewModel.loginUser(
                        username = username,
                        password = password
                    )
                    errorMessage = viewModel.errorMessage.value ?: ""
                    if (viewModel.isLoggedIn.value) {
                        onLoginSuccess()
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = onNavigateToRegister,

        ) {
            Text("Register",
                fontSize = 20.sp,
                color = androidx.compose.ui.graphics.Color(0xFF034B36),
                )
        }
    }
}

@Composable
fun passwordVisualTransformation(): VisualTransformation {
    return VisualTransformation { transformedText ->
        TransformedText(
            AnnotatedString("•".repeat(transformedText.text.length)),
            OffsetMapping.Identity
        )
    }
}

@Composable
fun PrintErrorMessage(text: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .border(1.dp, Color(0xFFD05F5F), shape = RoundedCornerShape(20.dp))

    ){
        Text(
            text = text,
            color = Color(0xFFD05F5F),
            fontSize = 14.sp,
            fontFamily = PoppinsBold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}