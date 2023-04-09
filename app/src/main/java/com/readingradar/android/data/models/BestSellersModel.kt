package com.readingradar.android.data.models

import androidx.room.*
import com.squareup.moshi.Json

data class BestSellersModel(
    val results: Results
)

data class Results(
    val lists: List<BooksList>
)

@Entity(tableName = "books_list_table")
data class BooksList(
    @PrimaryKey(autoGenerate = false) var id: Long? = null,
    @ColumnInfo(name = "display_name") @Json(name = "display_name") var displayName: String = "",
    @Ignore var books: List<Book> = listOf(),
)

@Entity(
    tableName = "books_table",
    foreignKeys = [
        ForeignKey(
            entity = BooksList::class,
            parentColumns = ["id"],
            childColumns = ["books_list_id"],
            onDelete = ForeignKey.CASCADE,
        )
    ],
    indices = [Index("books_list_id")],
)
data class Book(
    @PrimaryKey(autoGenerate = false) var author: String = "",
    @ColumnInfo(name = "books_list_id") var booksListId: Long? = null,
    @ColumnInfo(name = "book_image") @Json(name = "book_image") var bookImage: String = "",
    @Json(name = "book_image_width") var imageWidth: Int = 0,
    @Json(name = "book_image_height") var imageHeight: Int = 0,
    var contributor: String = "",
    var description: String = "",
    var publisher: String = "",
    @ColumnInfo(name = "isbn10") @Json(name = "primary_isbn10") var isbn: String = "",
    var rank: Int = 0,
    @ColumnInfo(name = "rank_last_week") @Json(name = "rank_last_week") var rankLastWeek: Int = 0,
    var title: String = "",
    @ColumnInfo(name = "weeks_on_list") @Json(name = "weeks_on_list") var weeksOnList: Int = 0,
)
