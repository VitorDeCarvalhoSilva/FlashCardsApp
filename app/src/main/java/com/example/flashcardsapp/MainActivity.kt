package com.example.flashcardsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import com.example.flashcardsapp.ui.screens.homePage.AssuntosScreen
import com.example.flashcardsapp.ui.theme.FlashCardsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlashCardsApp() // sua navegação inteira começa aqui
        }
    }
}
