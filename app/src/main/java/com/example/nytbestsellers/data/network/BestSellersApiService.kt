package com.example.nytbestsellers.data.network

import com.example.nytbestsellers.data.models.BestSellersModel
import com.example.nytbestsellers.data.models.GoogleBooksModel
import com.example.nytbestsellers.utils.ApiKey.NYT_API_KEY
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

private const val BASE_URL = " https://api.nytimes.com/svc/books/v3/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter("api-key", NYT_API_KEY)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .build()
    )
    .build()

interface BestSellersApiService {
    @GET("lists/full-overview.json")
    suspend fun getAllBestSellersBooks(): BestSellersModel

    @GET
    suspend fun getBookDescription(@Url url: String) : GoogleBooksModel

    // Return an instance of BestSellersApiService as a singleton
    companion object BestSellersApi {
        val retrofitService: BestSellersApiService by lazy {
            retrofit.create(BestSellersApiService::class.java)
        }
    }
}
