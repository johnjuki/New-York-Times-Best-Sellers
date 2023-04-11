package com.readingradar.android.data.repository

import android.util.Log
import com.readingradar.android.data.database.dao.RrDao
import com.readingradar.android.data.models.Book
import com.readingradar.android.data.models.BooksList
import com.readingradar.android.data.network.RrApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class RrRepositoryImpl @Inject constructor(
    private val rrApiService: RrApiService,
    private val rrDao: RrDao,
) : RrRepository {

    override fun getBestSellersList(): Flow<List<BooksList>>  {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            try {
                refreshCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        Log.d("Connection Exception", "$e")
                        rrDao.getBestSellersList().onCompletion { }
                            .collect {
                                if (it.isEmpty()) {
                                    // TODO: Prompt the user to connect to the internet
                                    Log.d("Exception", "No saved Best Sellers List")
                                }
                            }
                    }
                    else -> Log.d("Exception", "$e")
                }
            }
        }

        return rrDao.getBestSellersList()
    }

    override suspend fun addLists(lists: List<BooksList>) = rrDao.insertBooksListList(lists)

    override fun getBooks(booksListId: Int): Flow<List<Book>> = rrDao.getBooks(booksListId)

    override suspend fun getBook(isbn: String): Book = rrDao.getBook(isbn)

    override fun getBookDescription(url: String): Flow<String> = flow {
        try {
            val result = rrApiService.getBookDescription(url)
            emit(result.items.first().volumeInfo.description)
        } catch (e: Exception) {
            Log.d("GoogleBooksException", "$e")
        }
    }

    /** Update the database with data from the API */
    private suspend fun refreshCache() {
        val booksListList = rrApiService.getBestSellersList().results.lists
        rrDao.insertBooksListList(booksListList)
        for (booksList in booksListList) {
            for (book in booksList.books) {
                rrDao.insertBook(book)
            }
        }
    }
}
