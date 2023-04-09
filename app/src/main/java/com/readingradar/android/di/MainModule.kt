package com.readingradar.android.di

import android.content.Context
import com.readingradar.android.data.database.RrDatabase
import com.readingradar.android.data.database.dao.RrDao
import com.readingradar.android.data.network.RrApiService
import com.readingradar.android.data.repository.RrRepository
import com.readingradar.android.data.repository.RrRepositoryImpl
import com.readingradar.android.utils.ApiKey
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    private const val BASE_URL = " https://api.nytimes.com/svc/books/v3/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
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
                        .addQueryParameter("api-key", ApiKey.NYT_API_KEY)
                        .build()
                    chain.proceed(chain.request().newBuilder().url(url).build())
                }
                .build()
        )
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): RrApiService =
        retrofit.create(RrApiService::class.java)

    @Singleton
    @Provides
    fun provideRrDatabase(@ApplicationContext context : Context) = RrDatabase.getInstance(context)

    @Provides
    fun provideRrDao(rrDatabase: RrDatabase) : RrDao = rrDatabase.rrDao()

    @Provides
    fun provideRrRepository(rrRepositoryImpl: RrRepositoryImpl) : RrRepository = rrRepositoryImpl

}
