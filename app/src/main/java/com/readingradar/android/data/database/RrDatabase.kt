package com.readingradar.android.data.database

import android.content.Context
import androidx.room.*
import com.google.gson.Gson
import com.readingradar.android.data.database.dao.RrDao
import com.readingradar.android.data.models.Book
import com.readingradar.android.data.models.BooksList
import com.readingradar.android.utils.Converters
import com.readingradar.android.utils.GsonParser

@TypeConverters(Converters::class)
@Database(entities = [BooksList::class, Book::class], version = 8, exportSchema = false)
abstract class RrDatabase : RoomDatabase() {

    abstract fun rrDao(): RrDao

    companion object {

        @Volatile
        private var INSTANCE: RrDatabase? = null

        fun getInstance(context: Context): RrDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = buildDatabase(context)
                INSTANCE = instance
                return instance
            }
        }

        private fun buildDatabase(context: Context): RrDatabase = Room.databaseBuilder(
            context.applicationContext,
            RrDatabase::class.java,
            "best_sellers_database"
        ).addTypeConverter(Converters(GsonParser(Gson()))).fallbackToDestructiveMigration().build()
    }

}