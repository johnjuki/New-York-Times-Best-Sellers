package com.readingradar.android.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.readingradar.android.data.models.Book

@ProvidedTypeConverter
class Converters(private val jsonParser: JsonParser) {

    @TypeConverter
    fun fromBooksJson(json: String): List<Book> {
        return jsonParser.fromJson<ArrayList<Book>>(
            json,
            object : TypeToken<ArrayList<Book>>() {}.type,
        ) ?: emptyList()
    }

    @TypeConverter
    fun toBooksJson(books: List<Book>): String {
        return jsonParser.toJson(books, object : TypeToken<ArrayList<Book>>() {}.type) ?: "[]"
    }
}
