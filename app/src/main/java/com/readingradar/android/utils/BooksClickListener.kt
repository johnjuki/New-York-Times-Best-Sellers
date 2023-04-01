package com.readingradar.android.utils

import com.readingradar.android.data.models.Books

class BooksClickListener(val clickListener: (book: Books) -> Unit) {
    fun onClick(book: Books) = clickListener(book)
}