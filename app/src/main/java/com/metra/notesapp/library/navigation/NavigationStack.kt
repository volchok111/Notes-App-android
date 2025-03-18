package com.metra.notesapp.library.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.metra.notesapp.feature.add.ui.AddScreen
import com.metra.notesapp.feature.home.ui.HomeScreen
import com.metra.notesapp.feature.reminderdetails.ui.DetailsScreen
import com.metra.notesapp.feature.settings.SettingsScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationRouting.Home.route) {
        composable(NavigationRouting.Home.route) {
            HomeScreen(navController)
        }

        composable(NavigationRouting.Add.route) {
            AddScreen(navController)
        }

        composable(NavigationRouting.Settings.route) {
            SettingsScreen(navController)
        }

        composable(NavigationRouting.Details.route, arguments = listOf(navArgument("reminderId") { type = NavType.IntType })) { backStackEntry ->
            val reminderId = backStackEntry.arguments?.getInt("reminderId") ?: 0
            DetailsScreen(navController, reminderId)
        }
    }
}
