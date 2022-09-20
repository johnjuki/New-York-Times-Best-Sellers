package com.example.nytbestsellers.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytbestsellers.network.BestSellersApiService
import com.example.nytbestsellers.network.BestSellersModel
import com.example.nytbestsellers.network.Books
import com.example.nytbestsellers.network.Lists
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<List<Books>>()

    // The external immutable LiveData for the request status
    val status: LiveData<List<Books>> = _status


    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getAllBestSellers()
    }

    private fun getAllBestSellers() {
        viewModelScope.launch {
            try {
                val result = BestSellersApiService.retrofitService.getAllBestSellersBooks()
                val resultList = mutableListOf<BestSellersModel>()
                resultList.add(result)

                val listsList = mutableListOf<List<Lists>>()
                listsList.add(result.results.lists)

                val booksList = mutableListOf<List<Books>>()

                for (i in listsList) {
                    for (j in i) {
                        booksList.add(j.books)
                    }
                }

                val books = mutableListOf<Books>()

                for (i in booksList) {
                    for (j in i) {
                        books.add(j)
                    }
                }

                _status.value = books

            } catch (e: Exception) {
                Log.d("Exception", "$e")
            }
        }
    }

}
