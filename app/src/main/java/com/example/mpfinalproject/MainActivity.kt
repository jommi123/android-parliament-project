package com.example.mpfinalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mpfinalproject.ui.MemberDetailScreen
import com.example.mpfinalproject.ui.home.HomeScreen
import com.example.mpfinalproject.ui.theme.MPFinalProjectTheme

// 29.9.2024, Jommi Koljonen, 2013099

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MPFinalProjectTheme {
                HomeScreen()
            }
        }
    }
}



