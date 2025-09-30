package com.example.lottery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LotteryApp() }
    }
}

@Composable
fun LotteryApp() {
    val nav = rememberNavController()
    MaterialTheme {
        NavHost(navController = nav, startDestination = "home") {
            composable("home") { HomeScreen(onOpen = { id -> nav.navigate("lottery/$id") }) }
            composable("lottery/{id}", arguments = listOf(navArgument("id") { type = NavType.StringType })) { bs ->
                val id = bs.arguments?.getString("id") ?: "NY"
                LotteryScreen(id = id) { nav.popBackStack() }
            }
        }
    }
}

private val lotteries = listOf("NY Día","NY Noche","Florida","Georgia","Connecticut","New Jersey","Anguilla")

@Composable
fun HomeScreen(onOpen: (String) -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Loterías") }) }) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(16.dp).fillMaxSize().verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Hola, Liss. Elige una lotería:", style = MaterialTheme.typography.titleMedium)
            lotteries.forEach { name ->
                Button(onClick = { onOpen(name) }, modifier = Modifier.fillMaxWidth()) { Text(name) }
            }
        }
    }
}

@Composable
fun LotteryScreen(id: String, onBack: () -> Unit) {
    val numbers = if (id == "Anguilla") listOf("12","45","88") else listOf("317","640","952")
    Scaffold(topBar = { TopAppBar(title = { Text(id) }, navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }) }) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Números calientes (demo):", style = MaterialTheme.typography.titleMedium)
            Text(numbers.joinToString("  •  "), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Divider()
            Text("Top 3 fuertes:", style = MaterialTheme.typography.titleSmall)
            numbers.take(3).forEachIndexed { i, n -> Text("${i + 1}. $n") }
        }
    }
}
