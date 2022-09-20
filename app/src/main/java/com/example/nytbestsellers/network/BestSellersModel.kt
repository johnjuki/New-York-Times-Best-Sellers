package com.example.nytbestsellers.network

import com.squareup.moshi.Json

data class BestSellersModel(
    val results: Results
)

data class Results(
    val lists: List<Lists>
)

data class Lists(
    @Json(name = "display_name")val displayName: String,
    val books: List<Books>
)

data class Books(
    val author: String,
    @Json(name = "book_image") val bookImage: String,
    @Json(name = "book_image_width") val imageWidth: Int,
    @Json(name = "book_image_height") val imageHeight: Int,
    val contributor: String,
    val description: String,
    val publisher: String,
    val rank: Int,
    @Json(name = "rank_last_week") val rankLastWeek: Int,
    val title: String,
    @Json(name = "weeks_on_list") val weeksOnList: Int,
)
