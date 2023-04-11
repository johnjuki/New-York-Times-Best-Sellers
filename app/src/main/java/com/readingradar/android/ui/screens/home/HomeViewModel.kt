package com.readingradar.android.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.readingradar.android.data.repository.RrRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(repository: RrRepository) : ViewModel() {

    val uiState: StateFlow<HomeUiState> =
        repository.getBestSellersList().map { HomeUiState.Success(it) }
            .stateIn(
                viewModelScope,
                initialValue = HomeUiState.Loading,
                started = SharingStarted.WhileSubscribed(5_000),
            )
}
