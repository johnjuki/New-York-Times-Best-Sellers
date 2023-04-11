package com.readingradar.android.ui.screens.book_details

import com.readingradar.android.data.models.Book


data class BookDetailsUiState(
    val isLoading: Boolean = true,
    val book: Book = Book(),
)

