package com.readingradar.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.readingradar.android.ui.screens.home.HomeRoute

@Composable
fun RrNavHost(
    modifier: Modifier = Modifier,
    navController : NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = modifier,
    ) {
        composable(route = Screens.Home.route) {
            HomeRoute()
        }
    }
}
