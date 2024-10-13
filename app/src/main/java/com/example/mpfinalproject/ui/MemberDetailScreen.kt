package com.example.mpfinalproject.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mpfinalproject.R

// 29.9.2024, Jommi Koljonen, 2013099


@Composable
fun MemberDetailScreen(
    modifier: Modifier = Modifier,
    memberViewModel: MemberViewModel = viewModel(factory = MemberViewModel.Factory),
    memberListViewModel: MemberListViewModel = viewModel(factory = MemberListViewModel.Factory),
    seatNumber: Int?,
    navController: NavController
) {
    LaunchedEffect(seatNumber) {
        if (seatNumber != null) {
            memberListViewModel.getMemberBySeatNumber(seatNumber)
        }
    }
    Log.d("seatnumber_detail", seatNumber.toString())

    val memberUiState by memberViewModel.uiState.collectAsState()
    val memberListUiState by memberListViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            MemberAppBar(canNavigateBack = true,
                navigateUp = { navController.popBackStack() })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.size(20.dp))

            memberListUiState.selectedMember?.let {
                AsyncImage(model = ImageRequest.Builder(context = LocalContext.current)
                    .data("https://avoindata.eduskunta.fi/" + it.pictureUrl)
                    .diskCachePolicy(coil.request.CachePolicy.ENABLED)
                    .memoryCachePolicy(coil.request.CachePolicy.ENABLED)
                    .build(),
                    contentDescription = "parliament member picture")

                Text(text = "${it.firstname} ${it.lastname}")
                Text(text = it.party)
                Text(text = it.minister.toString())
                Text(text = it.seatNumber.toString())
            }

            UserRating(
                rating = memberUiState.starRating,
                onRatingChanged = { newRating -> memberViewModel.updateRating(newRating) }
            )

            Spacer(modifier = Modifier.size(20.dp))

            CommentSection(
                comment = memberUiState.comment,
                comments = memberUiState.comments,
                onCommentChanged = { memberViewModel.updateCommentInput(it) },
                onSubmitComment = { memberViewModel.addComment(it) }

            )

        }
    }
}

@Composable
fun UserRating(
    rating: Int,
    onRatingChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,

    ) {

    Row() {
        for (value in 1..5) {
            StarIcon(
                rating = rating,
                ratingValue = value,
                selectedColor = Color.Yellow,
                unselectedColor = Color.Gray,
                onRatingSelected = { onRatingChanged(value) }
            )

        }
    }
}

@Composable
fun StarIcon(
    rating: Int,
    ratingValue: Int,
    selectedColor: Color,
    unselectedColor: Color,
    onRatingSelected: () -> Unit,
    modifier: Modifier = Modifier
) {

    val tint = if (rating >= ratingValue) selectedColor else unselectedColor

    Icon(
        Icons.Sharp.Star, contentDescription = "star",
        tint = tint,
        modifier = Modifier
            .size(52.dp)
            .clickable { onRatingSelected() }
    )
}

@Composable
fun CommentSection(
    comment: String,
    comments: List<String>,
    onCommentChanged: (String) -> Unit,
    onSubmitComment: (String) -> Unit,

    modifier: Modifier = Modifier

) {


    Column {
        TextField(
            value = comment,
            onValueChange = onCommentChanged
        )

        Button(onClick = { onSubmitComment(comment) }) {
            Text(text = "Comment")
        }

        Text(text = "Comments:")

        LazyColumn {
            items(comments) { commentText ->
                Text(text = commentText, modifier = Modifier.padding(12.dp))
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {

}
