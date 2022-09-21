package com.example.nytbestsellers.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytbestsellers.BestSellersApplication
import com.example.nytbestsellers.data.database.BestSellersDb
import com.example.nytbestsellers.data.database.dao.BestSellersDao
import com.example.nytbestsellers.network.BestSellersApiService
import com.example.nytbestsellers.network.BestSellersModel
import com.example.nytbestsellers.network.Lists
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

class MainViewModel : ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<List<Lists>>()

    // The external immutable LiveData for the request status
    val status: LiveData<List<Lists>> = _status

    private lateinit var bestSellersDao: BestSellersDao

    /**
     * Call getBestSellers() on init so we can display status immediately.
     */
    init {
        getAllBestSellers()
    }

    private fun getAllBestSellers() {
        viewModelScope.launch {
            bestSellersDao = BestSellersDb.getDaoInstance(BestSellersApplication.getAppContext())

            try {
                refreshCache()

            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        Log.d("Connection Exception", "$e")
                        if (bestSellersDao.getAllLists().isEmpty())
                        // TODO: Prompt the use to connect to the internet
                            Log.d("Exception", "No saved Best Sellers Books")
                    }
                    else -> Log.d("Exception", "$e")
                }

            }

            // Singe Source Of Truth: Room Database

            _status.value = bestSellersDao.getAllLists()

        }
    }

    // Update the database with data from NYT Best Sellers Books API
    private suspend fun refreshCache() {
        val result = BestSellersApiService.retrofitService.getAllBestSellersBooks()
        val resultList = mutableListOf<BestSellersModel>()
        resultList.add(result)

        val listsList = mutableListOf<List<Lists>>()
        listsList.add(result.results.lists)

        val theLists = mutableListOf<Lists>()


        for (i in listsList) {
            for (j in i) {
                theLists.add(j)
            }
        }

        bestSellersDao.addLists(theLists)
    }

}
