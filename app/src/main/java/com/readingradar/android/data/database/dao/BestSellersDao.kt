package com.readingradar.android.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.readingradar.android.data.models.Lists

// TODO: Delete a record from the database when its no longer in the list
@Dao
interface BestSellersDao {
    @Query("SELECT * FROM lists_table")
    suspend fun getAllLists() : List<Lists>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLists(lists: List<Lists>)
}
