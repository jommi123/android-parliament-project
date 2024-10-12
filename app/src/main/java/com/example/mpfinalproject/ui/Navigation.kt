package com.example.mpfinalproject.ui

import android.app.FragmentManager.BackStackEntry
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mpfinalproject.ui.home.HomeScreen


// 12.10.2024


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = "PM App") },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = { navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back button"
                    )
                }
            }
        }
    )
}


@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(onNavigateToMemberDetail = { seatNumber ->
                navController.navigate("memberDetail/$seatNumber")
            })
        }
        composable("memberDetail/{seatNumber}") { backStackEntry ->
            val seatNumber = backStackEntry.arguments?.getString("seatNumber")?.toIntOrNull()
            MemberDetailScreen(seatNumber = seatNumber, navController = navController)
        }
    }
}