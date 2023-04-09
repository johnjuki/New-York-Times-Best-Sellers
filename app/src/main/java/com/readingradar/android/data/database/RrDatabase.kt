package com.readingradar.android.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.readingradar.android.converters.Converters
import com.readingradar.android.data.database.dao.RrDao
import com.readingradar.android.data.models.Books
import com.readingradar.android.data.models.Lists
import com.readingradar.android.utils.GsonParser

@TypeConverters(Converters::class)
@Database(entities = [Lists::class, Books::class], version = 5, exportSchema = false)
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