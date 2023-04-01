package com.readingradar.android.data.repository

//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.example.nytbestsellers.BestSellersApplication
//import com.example.nytbestsellers.data.database.BestSellersDb
//import com.example.nytbestsellers.data.database.dao.BestSellersDao
//import com.example.nytbestsellers.data.models.Lists
//import com.example.nytbestsellers.data.network.BestSellersApiService
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import retrofit2.HttpException
//import java.net.ConnectException
//import java.net.UnknownHostException
//
//class Repository {
//    private lateinit var bestSellersDao: BestSellersDao
//
//    // The internal MutableLiveData that stores the status of the most recent request
//    private val _bestSellersLists = MutableLiveData<List<Lists>>()
//
//    // The external immutable LiveData for the request status
//    val bestSellersLists: LiveData<List<Lists>> = _bestSellersLists
//
//    private val _bookDescription = MutableLiveData<String>()
//
//    val bookDescription: LiveData<String> = _bookDescription
//
//    val scope = CoroutineScope(Dispatchers.IO)
//
//    init {
//        getAllBestSellers()
//    }
//
//    private fun getAllBestSellers() {
//        scope.launch {
//            bestSellersDao = BestSellersDb.getDaoInstance(BestSellersApplication.getAppContext())
//
//            try {
//                refreshCache()
//
//            } catch (e: Exception) {
//                when (e) {
//                    is UnknownHostException,
//                    is ConnectException,
//                    is HttpException -> {
//                        Log.d("Connection Exception", "$e")
//                        if (bestSellersDao.getAllLists().isEmpty())
//                        // TODO: Prompt the use to connect to the internet
//                            Log.d("Exception", "No saved Best Sellers Books")
//                    }
//                    else -> Log.d("Exception", "$e")
//                }
//
//            }
//
//            _bestSellersLists.value = bestSellersDao.getAllLists()
//        }
//    }
//
//    // Update the database with data from NYT Best Sellers Books API
//    private suspend fun refreshCache() {
//        val result = BestSellersApiService.retrofitService.getAllBestSellersBooks()
//
//        val listsList = mutableListOf<List<Lists>>()
//        listsList.add(result.results.lists)
//
//        val theLists = mutableListOf<Lists>()
//
//
//        for (i in listsList) {
//            for (j in i) {
//                theLists.add(j)
//            }
//        }
//
//        bestSellersDao.addLists(theLists)
//    }
//
//    fun getTheBookDescription(url: String) {
//        scope.launch {
//            try {
//                val result = BestSellersApiService.retrofitService.getBookDescription(url)
//                Log.d("GoogleBooks", "$result")
//                _bookDescription.value = result.items.first().volumeInfo.description
//            } catch (e: Exception) {
//                Log.d("GoogleBooksException","$e")
//            }
//
//        }
//    }
//}
