package com.readingradar.android.ui.screens.book_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.readingradar.android.data.repository.RrRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val repository: RrRepository,
) : ViewModel() {

    var uiState by mutableStateOf(BookDetailsUiState())

    fun getBook(isbn: String) = viewModelScope.launch {
        val book = repository.getBook(isbn)
        uiState = uiState.copy(isLoading = false, book = book)
        val description = repository.getBookDescription(
            url = "https://www.googleapis.com/books/v1/volumes?q=isbn:${book.isbn}",
        )
        uiState = uiState.copy(description = description)
    }
}
