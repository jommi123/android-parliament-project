package com.example.mpfinalproject.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
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

// 4.10.2024, Jommi Koljonen, 2013099


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    memberListViewModel: MemberListViewModel = viewModel(factory = MemberListViewModel.Factory),
) {
    val uiState by memberListViewModel.uiState.collectAsState()

    LazyColumn() {
        items(uiState.members) { member ->
            MemberCard(member)

        }
    }
    
}



//@Composable
//fun LoadingScreen() {
//    Text(text = "Loading")
//}
//
//@Composable
//fun ErrorScreen() {
//    Text(text = "Error")
//}
//
//@Composable
//fun ResultScreen(members: String) {
//    Box(modifier = Modifier) {
//        Text(text = members)
//    }
//}


@Composable
fun MemberCard(
    member: ParliamentMember,
    modifier: Modifier = Modifier) {
    Card {
        Row {
//            Image(
//                painter = painterResource(id = R.drawable.haavisto),
//                contentDescription = "haavisto",
//                modifier = Modifier
//                    .height(120.dp)
//            )

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("https://avoindata.eduskunta.fi/" + member.pictureUrl)
                    .build(),
                contentDescription = "parliament member picture")

            Text(text = member.firstname)

        }
    }
}

@Preview
@Composable
fun ListScreenPreview() {

}

