package com.example.nytbestsellers.utils

import com.example.nytbestsellers.data.models.Books

class BooksClickListener(val clickListener: (book: Books) -> Unit) {
    fun onClick(book: Books) = clickListener(book)
}