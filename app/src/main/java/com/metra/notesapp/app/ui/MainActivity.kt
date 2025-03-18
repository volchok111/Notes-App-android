package com.metra.notesapp.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.metra.notesapp.library.navigation.NavigationStack
import com.metra.notesapp.library.ui.NotesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {
                MainScreen()
            }

            NavigationStack()
        }
    }
}
