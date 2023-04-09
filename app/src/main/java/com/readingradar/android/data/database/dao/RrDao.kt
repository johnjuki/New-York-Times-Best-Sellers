package com.readingradar.android.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.readingradar.android.data.models.Book
import com.readingradar.android.data.models.BooksList
import kotlinx.coroutines.flow.Flow

// TODO: Delete a record from the database when its no longer in the list
@Dao
interface RrDao {
    @Query("SELECT * FROM books_list_table")
    fun getAllLists() : Flow<List<BooksList>>

    @Query("SELECT * FROM books_table WHERE books_list_id = :booksListId")
    fun getBooks(booksListId: Long) : Flow<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooksListList(lists: List<BooksList>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)
}
