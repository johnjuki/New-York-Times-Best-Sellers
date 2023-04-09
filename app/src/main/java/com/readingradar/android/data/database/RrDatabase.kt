package com.readingradar.android.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.readingradar.android.data.database.dao.RrDao
import com.readingradar.android.data.models.Book
import com.readingradar.android.data.models.BooksList

@Database(entities = [BooksList::class, Book::class], version = 7, exportSchema = false)
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
        ).fallbackToDestructiveMigration().build()
    }

}