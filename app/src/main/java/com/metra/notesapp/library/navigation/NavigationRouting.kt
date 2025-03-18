package com.metra.notesapp.library.navigation

sealed class NavigationRouting(val route: String) {

    object Home : NavigationRouting("home")

    object Add : NavigationRouting("add")

    object Settings : NavigationRouting("settings")

    object Details : NavigationRouting("details/{reminderId}") {
        fun createRoute(reminderId: Int) = "details/$reminderId"
    }
}
