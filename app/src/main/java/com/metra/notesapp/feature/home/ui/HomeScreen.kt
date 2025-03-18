package com.metra.notesapp.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.metra.notesapp.library.navigation.NavigationRouting

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(android.graphics.Color.parseColor("#59469d")),
                        Color(android.graphics.Color.parseColor("#643d67"))
                    )
                )
            )
    ) {
        Column(modifier = androidx.compose.ui.Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Home Screen", fontSize = 24.sp, modifier = Modifier.padding(top = 48.dp))

            Button(onClick = { navController.navigate(NavigationRouting.Add.route) }) {
                Text("Add Reminder")
            }
            Button(onClick = { navController.navigate(NavigationRouting.Settings.route) }) {
                Text("Settings")
            }

            Button(onClick = { navController.navigate(NavigationRouting.Details.createRoute(reminderId = 1)) }) {
                Text("Details of Reminder with ID 1")
            }
        }
    }
}
