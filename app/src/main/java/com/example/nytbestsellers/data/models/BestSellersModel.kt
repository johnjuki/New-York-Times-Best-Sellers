package com.example.nytbestsellers.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class BestSellersModel(
    val results: Results
)

data class Results(
    val lists: List<Lists>
)

@Entity(tableName = "lists_table")
data class Lists(
    @PrimaryKey
    @ColumnInfo(name = "display_name")
    @Json(name = "display_name") val displayName: String,
    val books: List<Books>
)

@Entity(tableName = "books_table")
@Parcelize
data class Books(
    @PrimaryKey
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "book_image") @Json(name = "book_image") val bookImage: String,
    @ColumnInfo(name = "width") @Json(name = "book_image_width") val imageWidth: Int,
    @ColumnInfo(name = "height") @Json(name = "book_image_height") val imageHeight: Int,
    @ColumnInfo(name = "contributor") val contributor: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "publisher") val publisher: String,
    @ColumnInfo(name = "isbn10") @Json(name = "primary_isbn10") val isbn: String,
    @ColumnInfo(name = "rank") val rank: Int,
    @ColumnInfo(name = "rank_last_week") @Json(name = "rank_last_week") val rankLastWeek: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "weeks_on_list") @Json(name = "weeks_on_list") val weeksOnList: Int,
) : Parcelable
