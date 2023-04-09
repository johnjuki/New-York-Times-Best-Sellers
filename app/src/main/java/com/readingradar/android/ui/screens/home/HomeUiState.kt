package com.readingradar.android.ui.screens.home

import com.readingradar.android.data.models.BooksList

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(val bestSellersLists: List<BooksList>) : HomeUiState
    // TODO: Add Error State
}
