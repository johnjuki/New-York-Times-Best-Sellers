package com.readingradar.android.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val homeUiState by viewModel.uiState.collectAsState()

    HomeScreen(homeUiState = homeUiState)
}

@Composable
fun HomeScreen(homeUiState: HomeUiState, modifier: Modifier = Modifier,) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when(homeUiState) {
            is HomeUiState.Loading -> CircularProgressIndicator()
            is HomeUiState.Success -> Text(text = homeUiState.bestSellersLists.size.toString())
        }
    }
}
