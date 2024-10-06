package com.example.mpfinalproject.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mpfinalproject.R

// 29.9.2024, Jommi Koljonen, 2013099


@Composable
fun MinisterScreen(
    modifier: Modifier = Modifier,
    ministerViewModel: MinisterViewModel = viewModel(),
) {
    val ministerUiState by ministerViewModel.uiState.collectAsState()

    val ratingState = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.haavisto),
            contentDescription = "placeholder",
            modifier = Modifier

                .padding(top = 80.dp)
        )

        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = "Pekka Haavisto jne",
            modifier = Modifier

        )

        UserRating(ratingState = ratingState)

        Spacer(modifier = Modifier.size(20.dp))

        TextField(value = "add comment", onValueChange = {})

    }
}

@Composable
fun UserRating(
    modifier: Modifier = Modifier,
    ratingState: MutableState<Int> = remember { mutableIntStateOf(0) }
) {

    Row() {
        for (value in 1..5) {
            StarIcon(
                ratingState = ratingState,
                ratingValue = value,
                selectedColor = Color.Yellow,
                unselectedColor = Color.Gray
            )

        }
    }
}

@Composable
fun StarIcon(
    ratingState: MutableState<Int>,
    ratingValue: Int,
    selectedColor: Color,
    unselectedColor: Color,
    modifier: Modifier = Modifier
) {

    val tint = if (ratingState.value >= ratingValue) selectedColor else unselectedColor

    Icon(
        Icons.Sharp.Star, contentDescription = "star",
        tint = tint,
        modifier = Modifier
            .size(52.dp)
            .clickable { ratingState.value = ratingValue }
    )
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    MinisterScreen()
}
