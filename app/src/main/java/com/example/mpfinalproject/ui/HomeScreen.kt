package com.example.mpfinalproject.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mpfinalproject.model.ParliamentMember

// 4.10.2024, Jommi Koljonen, 2013099


@Composable
fun HomeScreen(
    memberListViewModel: MemberListViewModel = viewModel(factory = MemberListViewModel.Factory),
    onNavigateToMemberDetail: (Int) -> Unit
) {
    val uiState by memberListViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            MemberAppBar(canNavigateBack = false,
                navigateUp = {})
        }
    ) { padding ->
        LazyColumn {
            items(uiState.members) { member ->
                MemberCard(
                    member,
                    modifier = Modifier.padding(padding),
                    onNavigateToMemberDetail = onNavigateToMemberDetail
                )
            }
        }
    }
}

@Composable
fun MemberCard(
    member: ParliamentMember,
    onNavigateToMemberDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("https://avoindata.eduskunta.fi/" + member.pictureUrl)
                    .diskCachePolicy(coil.request.CachePolicy.ENABLED)
                    .memoryCachePolicy(coil.request.CachePolicy.ENABLED)
                    .build(),
                contentDescription = "parliament member picture"
            )

            Text(text = "${member.firstname} ${member.lastname}")

            Button(onClick = { onNavigateToMemberDetail(member.seatNumber) }) {
                Text(text = "View details")
            }
        }
    }
}