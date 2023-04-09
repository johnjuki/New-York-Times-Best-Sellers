package com.readingradar.android.navigation

sealed class Screens(val route: String) {

    object Home: Screens(route = "home_route")

}
