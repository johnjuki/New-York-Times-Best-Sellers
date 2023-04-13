package com.readingradar.android.ui

import com.readingradar.android.data.models.Book
import com.readingradar.android.data.models.BooksList

object DummyContent {
    fun getBestSellersList() = arrayListOf(
        BooksList(
            id = 0,
            displayName = "Combined Print & E-Book Fiction",
            books = arrayListOf(book)
        ),
        BooksList(
            id = 1,
            displayName = "Young Adult Paperback",
            books = arrayListOf(
                Book(
                    author = "Laura Nowlin",
                    booksListId = 1,
                    bookImage = "https://storage.googleapis.com/du-prd/books/images/9781402277832.jpg",
                    imageWidth = 330,
                    imageHeight = 495,
                    contributor = "by Laura Nowlin",
                    description = "",
                    publisher = "Sourcebooks Fire",
                    isbn = "1728205484",
                    rank = 1,
                    rankLastWeek = 0,
                    title = "IF HE HAD BEEN WITH ME",
                    weeksOnList = 0,
                )
            )
        ),
    )

    val book = Book(
        author = "Taylor Jenkins Reid",
        booksListId = 0,
        bookImage = "https://storage.googleapis.com/du-prd/books/images/9781524798628.jpg",
        imageWidth = 325,
        imageHeight = 495,
        contributor = "by Taylor Jenkins Reid",
        description = "A fictional oral history charting the rise and fall of a ’70s rock ’n’ roll band.",
        publisher = "Ballantine",
        isbn = "1524798649",
        rank = 1,
        rankLastWeek = 3,
        title = "DAISY JONES & THE SIX",
        weeksOnList = 13,
    )
}
