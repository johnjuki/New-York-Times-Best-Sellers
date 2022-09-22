package com.example.nytbestsellers.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nytbestsellers.converters.Converters
import com.example.nytbestsellers.data.database.dao.BestSellersDao
import com.example.nytbestsellers.data.models.Books
import com.example.nytbestsellers.data.models.Lists
import com.example.nytbestsellers.utils.GsonParser
import com.google.gson.Gson

@TypeConverters(Converters::class)
@Database(entities = [Lists::class, Books::class], version = 5, exportSchema = false)
abstract class BestSellersDb : RoomDatabase() {

    abstract val bestSellersDao: BestSellersDao

    companion object {

        @Volatile
        private var INSTANCE: BestSellersDao? = null

        fun getDaoInstance(context: Context): BestSellersDao {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = buildDatabase(context).bestSellersDao
                    INSTANCE = instance
                }
                return instance
            }
        }

        private fun buildDatabase(context: Context): BestSellersDb = Room.databaseBuilder(
            context.applicationContext,
            BestSellersDb::class.java,
            "best_sellers_database"
        ).addTypeConverter(Converters(GsonParser(Gson()))).fallbackToDestructiveMigration().build()
    }

}