package com.readingradar.android.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.readingradar.android.data.models.Books
import com.readingradar.android.utils.JsonParser

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
