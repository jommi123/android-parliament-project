package com.example.mpfinalproject.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mpfinalproject.R
import com.example.mpfinalproject.network.ParliamentMember

// 4.10.2024, Jommi Koljonen, 2013099


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    memberListViewModel: MemberListViewModel = viewModel(),
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
            Text(text = member.firstname)

        }
    }
}

@Preview
@Composable
fun ListScreenPreview() {

}

