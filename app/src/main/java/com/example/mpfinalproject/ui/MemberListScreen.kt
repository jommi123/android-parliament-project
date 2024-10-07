package com.example.mpfinalproject.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mpfinalproject.R

// 4.10.2024, Jommi Koljonen, 2013099


enum class MinisterList() {
    Start,
    Minister,
}

@Composable
fun MinisterListScreen(modifier: Modifier = Modifier) {
    LazyColumn {

    }

}

@Composable
fun MinisterCard(modifier: Modifier = Modifier) {
    Card {
        Row {
            Image(
                painter = painterResource(id = R.drawable.haavisto),
                contentDescription = "haavisto",
                modifier = Modifier
                    .height(120.dp)
            )
            Text(text = "Pekka haavisto")

        }
    }
}

@Preview
@Composable
fun ListScreenPreview() {
    MinisterCard()
}

