package com.example.flashcardsapp

import android.annotation.SuppressLint
import android.graphics.fonts.FontFamily
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashcardsapp.ui.theme.FlashCardsAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlashCardsAppTheme {
                Scaffold(
                    modifier = Modifier.padding(horizontal = 30.dp)
                ) {
                    AssuntosScreen()
                }
            }
        }
    }
}

@Composable
fun AssuntosScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopBar()
        Title("Assuntos")
        Spacer(modifier = Modifier.height(24.dp))

        SubjectCard("Inglês")
        Spacer(modifier = Modifier.height(12.dp))
        SubjectCard("Inglês")
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 60.dp),

    ) {
        IconButton(
            onClick = { /*TODO*/ }

        ){
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu"
            )
        }

    }
}

@Composable
fun Title(text: String){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Assuntos",
            fontSize = 22.sp,

        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = { /* ação do botão + */ }) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Adicionar assunto",
                tint = Color(0xFF008C6D)
            )
        }
    }
}

@Composable
fun SubjectCard(text: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2AC2A3)),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Ir para assunto",
                tint = Color.White
            )
        }
    }
}