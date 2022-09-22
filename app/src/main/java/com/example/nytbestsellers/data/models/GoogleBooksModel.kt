package com.example.nytbestsellers.data.models

data class GoogleBooksModel(
    val items: List<Items>
)

data class Items(
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val description: String
)
