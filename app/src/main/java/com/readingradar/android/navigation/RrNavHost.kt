package com.readingradar.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.readingradar.android.ui.screens.book_details.BookDetailsRoute
import com.readingradar.android.ui.screens.home.HomeRoute
import com.readingradar.android.utils.Constants.BOOK_ISBN_KEY

@Composable
fun RrNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = modifier,
    ) {

        // HOME
        composable(route = Screens.Home.route) {
            HomeRoute(
                onBookCoverClick = { isbn ->
                    navController.navigate(Screens.BookDetails.replaceIsbnKey(isbn))
                },
            )
        }

        // BOOK DETAILS
        composable(
            route = Screens.BookDetails.route,
            arguments = listOf(navArgument(BOOK_ISBN_KEY) { type = NavType.StringType })
        ) { navBackStackEntry ->
            val isbn = navBackStackEntry.arguments!!.getString(BOOK_ISBN_KEY)!!
            BookDetailsRoute(isbn = isbn, navigateUp = { navController.navigateUp() },)
        }
    }
}
