package com.readingradar.android.navigation

import com.readingradar.android.utils.Constants.BOOK_ISBN_KEY

sealed class Screens(val route: String) {

    object Home: Screens(route = "home_route")

    object BookDetails: Screens(route = "book_details_route/{$BOOK_ISBN_KEY}") {
        fun replaceIsbnKey(isbnKey: String) : String {
            return this.route.replace("{$BOOK_ISBN_KEY}", isbnKey)
        }
    }

}
