package com.example.mpfinalproject.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mpfinalproject.model.ParliamentMember
import com.example.mpfinalproject.ui.MemberListViewModel

// 4.10.2024, Jommi Koljonen, 2013099

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
fun HomeScreen(
    modifier: Modifier = Modifier,
    memberListViewModel: MemberListViewModel = viewModel(factory = MemberListViewModel.Factory),
) {
    val uiState by memberListViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            MemberAppBar(canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        }
    ) { padding ->
        LazyColumn() {
            items(uiState.members) { member ->
                MemberCard(member, modifier = Modifier.padding(padding))

            }
        }

    }


}


@Composable
fun MemberCard(
    member: ParliamentMember,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column {
            Row {

                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data("https://avoindata.eduskunta.fi/" + member.pictureUrl)
                        .diskCachePolicy(coil.request.CachePolicy.ENABLED)
                        .memoryCachePolicy(coil.request.CachePolicy.ENABLED)
                        .build(),
                    contentDescription = "parliament member picture"
                )

                Text(text = member.firstname)

            }
            Button(onClick = { /*TODO*/ }) {

            }
        }
    }
}

@Preview
@Composable
fun ListScreenPreview() {
    MemberAppBar(canNavigateBack = true, navigateUp = { /*TODO*/ })
}

