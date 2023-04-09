package com.readingradar.android.data.repository

import com.readingradar.android.data.models.Lists
import kotlinx.coroutines.flow.Flow

interface RrRepository {

    fun getBestSellersLists() : Flow<List<Lists>>

    suspend fun addLists(lists: List<Lists>)

    suspend fun getBookDescription(url: String) : Flow<String>

}
