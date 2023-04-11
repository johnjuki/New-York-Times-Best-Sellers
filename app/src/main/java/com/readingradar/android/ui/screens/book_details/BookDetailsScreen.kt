package com.readingradar.android.ui.screens.book_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BookDetailsRoute(
    isbn: String,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookDetailsViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit ) {
        viewModel.getBook(isbn)
    }
    val uiState = viewModel.uiState
    BookDetailsScreen(uiState, navigateUp)
}

@Composable
fun BookDetailsScreen(
    uiState: BookDetailsUiState,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        val book = uiState.book
        Text(text = book.title)
    }
}
