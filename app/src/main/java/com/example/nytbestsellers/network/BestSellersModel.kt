package com.example.nytbestsellers.network

import com.squareup.moshi.Json

data class BestSellersModel(
    val results: Results
)

data class Results(
    val lists: List<Lists>
)

data class Lists(
    val books: List<Books>
)

data class Books(
    val author: String,
    @Json(name = "book_image") val bookImage: String,
    val description: String,
    val rank: Int,
    @Json(name = "rank_last_week") val rankLastWeek: Int,
    val title: String,
    @Json(name = "weeks_on_list") val weeksOnList: Int,
)
