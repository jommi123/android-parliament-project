package com.example.mpfinalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mpfinalproject.ui.HomeScreen
import com.example.mpfinalproject.ui.MemberListViewModel
import com.example.mpfinalproject.ui.MinisterScreen
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



