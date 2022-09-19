package com.example.nytbestsellers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytbestsellers.network.BestSellersApiService
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status
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
                _status.value = "Success: $result Books retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

}
