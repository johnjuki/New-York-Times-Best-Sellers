package com.readingradar.android.data.repository

import com.readingradar.android.data.models.Book
import com.readingradar.android.data.models.BooksList
import kotlinx.coroutines.flow.Flow

interface RrRepository {

    fun getBestSellersList() : Flow<List<BooksList>>

    suspend fun addLists(lists: List<BooksList>)

    suspend fun getBooks(booksListId: Long) : Flow<List<Book>>

    suspend fun getBookDescription(url: String) : Flow<String>

}
