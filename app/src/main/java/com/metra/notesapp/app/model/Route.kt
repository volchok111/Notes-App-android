package com.metra.notesapp.app.model

enum class Route {
    Home,
    Details,
    Settings;

    operator fun invoke() = name.lowercase()

    companion object {
        val Initial = Home
    }
}
