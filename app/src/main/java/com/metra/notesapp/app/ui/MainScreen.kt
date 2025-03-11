package com.metra.notesapp.app.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.metra.notesapp.library.ui.NotesAppTheme

@Composable
fun MainScreen() {
    MainScreenImpl()
}

@Composable
fun MainScreenImpl() {
    Text(
        text = "Hello Android"
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesAppTheme {
        MainScreenImpl()
    }
}
