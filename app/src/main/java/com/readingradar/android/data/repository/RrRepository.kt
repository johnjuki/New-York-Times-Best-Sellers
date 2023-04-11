package com.readingradar.android.data.repository

import com.readingradar.android.data.models.Book
import com.readingradar.android.data.models.BooksList
import kotlinx.coroutines.flow.Flow

interface RrRepository {

    fun getBestSellersList() : Flow<List<BooksList>>

    suspend fun addLists(lists: List<BooksList>)

    fun getBooks(booksListId: Int) : Flow<List<Book>>

    suspend fun getBook(isbn: String) : Book

    fun getBookDescription(url: String) : Flow<String>

}
