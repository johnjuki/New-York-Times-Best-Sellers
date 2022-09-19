package com.example.nytbestsellers.repository

import com.example.nytbestsellers.network.BestSellersApiService

class BestSellersApiRepo(private val bestSellersApiService: BestSellersApiService) {
    suspend fun getAllBestSellers() = bestSellersApiService.getAllBestSellersBooks()
}
