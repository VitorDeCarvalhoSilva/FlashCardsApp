package com.example.flashcardsapp.ui.screens.register

import PoppinsBold
import PoppinsRegular
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashcardsapp.ui.components.Capriola
import com.example.flashcardsapp.ui.components.RoundedOutlinedButton
import com.example.flashcardsapp.ui.viewmodels.AuthViewModel

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    authViewModel: AuthViewModel
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String>("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Register",
                fontSize = 36.sp,
                fontFamily = Capriola,
                color = Color(0xFF034B36),
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        if(errorMessage.isNotEmpty()){
            PrintErrorMessage(text = errorMessage)
        }
        Spacer(modifier = Modifier.height(8.dp))
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
                focusedBorderColor = Color(0xFF034B36),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFF034B36),
                cursorColor = Color(0xFF034B36)
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
                focusedBorderColor = Color(0xFF034B36),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFF034B36),
                cursorColor = Color(0xFF034B36)
            ),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                passwordError = password != it
            },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            isError = passwordError,
            textStyle = TextStyle(
                fontFamily = PoppinsBold,
                fontSize = 18.sp,
                color = Color.Black
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF034B36),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFF034B36),
                cursorColor = Color(0xFF034B36)
            ),
            maxLines = 1
        )

        if (passwordError) {
            Text(
                text = "Passwords do not match",
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        val viewModel: AuthViewModel = viewModel()
        RoundedOutlinedButton(
            text = "Register",
            onClick = {
                if(
                    username.isEmpty() ||
                    password.isEmpty() ||
                    confirmPassword.isEmpty()
                ){
                    errorMessage = "❌   Preencha todos os campos!"
                }else {
                    viewModel.registerUser(
                        username = username,
                        password = password
                    )
                    errorMessage = viewModel.errorMessage.value ?: ""
                    if (viewModel.isRegistered.value) {
                        onNavigateToLogin()
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onNavigateToLogin) {
            Text(
                text = "Login",
                fontSize = 20.sp,
                color = Color(0xFF034B36)
            )
        }

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