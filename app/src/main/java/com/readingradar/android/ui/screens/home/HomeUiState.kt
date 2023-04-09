package com.readingradar.android.ui.screens.home

import com.readingradar.android.data.models.Lists

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(val bestSellersLists: List<Lists>) : HomeUiState
    // TODO: Add Error State
}
