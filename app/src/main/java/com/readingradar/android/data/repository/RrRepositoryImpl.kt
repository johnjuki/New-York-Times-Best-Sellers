package com.readingradar.android.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.readingradar.android.data.database.dao.RrDao
import com.readingradar.android.data.models.BestSellersModel
import com.readingradar.android.data.models.Lists
import com.readingradar.android.data.network.RrApiService
import com.readingradar.android.utils.getJsonDataFromAsset
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class RrRepositoryImpl @Inject constructor(
    private val context: Context,
    private val rrApiService: RrApiService,
    private val rrDao: RrDao,
) : RrRepository {

    override fun getBestSellersLists(): Flow<List<Lists>> = flow {
//        val scope = CoroutineScope(Dispatchers.IO)
//        scope.launch {
            try {
                refreshCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        Log.d("Connection Exception", "$e")
                        rrDao.getAllLists().onCompletion { }
                            .collect {
                                if (it.isEmpty()) {
                                    // TODO: Prompt the user to connect to the internet
                                    Log.d("Exception", "No saved Best Sellers List")

                                    // Load from assets. TODO: REMOVE!!
                                    val jsonFileString =
                                        getJsonDataFromAsset(context, "best_sellers.json")
                                    val bestSellersModelType =
                                        object : TypeToken<BestSellersModel>() {}.type
                                    val result: BestSellersModel =
                                        Gson().fromJson(jsonFileString, bestSellersModelType)
                                    val listsList = mutableListOf<List<Lists>>()
                                    listsList.add(result.results.lists)
                                    val theLists = mutableListOf<Lists>()
                                    for (i in listsList) {
                                        for (j in i) {
                                            theLists.add(j)
                                        }
                                    }
                                    Log.d("lists", theLists.size.toString())

//                                    rrDao.addLists(theLists) // TODO: Display name Not Null constraint error when adding to database
                                    emit(theLists)
                                }
                            }
                    }
                    else -> Log.d("Exception", "$e")
                }
            }
//        }

//        return rrDao.getAllLists()
    }

    override suspend fun addLists(lists: List<Lists>) = rrDao.addLists(lists)

    override suspend fun getBookDescription(url: String): Flow<String> = flow {
        try {
            val result = rrApiService.getBookDescription(url)
            emit(result.items.first().volumeInfo.description)
        } catch (e: Exception) {
            Log.d("GoogleBooksException", "$e")
        }
    }

    /** Update the database with data from the API */
    private suspend fun refreshCache() {
        val result = rrApiService.getAllBestSellersBooks()

        val listsList = mutableListOf<List<Lists>>()
        listsList.add(result.results.lists)

        val theLists = mutableListOf<Lists>()


        for (i in listsList) {
            for (j in i) {
                theLists.add(j)
            }
        }

        rrDao.addLists(theLists)
    }
}
