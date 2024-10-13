package com.example.mpfinalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mpfinalproject.ui.AppNavGraph
import com.example.mpfinalproject.ui.theme.MPFinalProjectTheme

// 29.9.2024, Jommi Koljonen, 2013099

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MPFinalProjectTheme {
                PmApp()
            }
        }
    }
}

@Composable
fun PmApp() {
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        AppNavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}