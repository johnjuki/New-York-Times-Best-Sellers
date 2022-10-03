package com.example.nytbestsellers.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class ListsWithBooks(
    @Embedded val lists: Lists,
    @Relation(
        parentColumn = "display_name",
        entityColumn = "display_name"
    )
    val books: List<Books>
)
