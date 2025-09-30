package com.example.lotteryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LotteryScreen()
                }
            }
        }
    }
}

@Composable
fun LotteryScreen() {
    var number by remember { mutableStateOf("—") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize().padding(24.dp)
    ) {
        Text("LotteryApp", style = MaterialTheme.typography.titleLarge)
        Text(number, fontSize = 48.sp)
        Button(onClick = {
            number = (0..999).random().toString().padStart(3, '0')
        }) { Text("GENERAR NÚMERO") }
    }
}
