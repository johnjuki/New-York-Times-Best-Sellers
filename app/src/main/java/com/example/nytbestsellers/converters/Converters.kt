package com.example.nytbestsellers.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.nytbestsellers.data.models.Books
import com.example.nytbestsellers.utils.JsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(private val jsonParser: JsonParser) {

    @TypeConverter
    fun toBooksJson(books: List<Books>): String {
        return jsonParser.toJson(books, object : TypeToken<ArrayList<Books>>() {}.type) ?: "[]"
    }

    @TypeConverter
    fun fromBooksJson(json: String): List<Books> {
        return jsonParser.fromJson<ArrayList<Books>>(
            json, object : TypeToken<ArrayList<Books>>() {}.type
        ) ?: emptyList()
    }
}
