package com.readingradar.android.data.network

import com.readingradar.android.data.models.BestSellersModel
import com.readingradar.android.data.models.GoogleBooksModel
import retrofit2.http.GET
import retrofit2.http.Url

interface RrApiService {
    @GET("lists/full-overview.json")
    suspend fun getAllBestSellersBooks(): BestSellersModel

    @GET
    suspend fun getBookDescription(@Url url: String) : GoogleBooksModel
}
