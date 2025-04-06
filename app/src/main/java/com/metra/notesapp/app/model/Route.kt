package com.metra.notesapp.app.model

/**
 * Enum representing the navigation routes within the application
 * Defines the available destinations that can be used for navigation
 */

enum class Route {
    Home,
    Add,
    Settings;

    /**
     * Utility function for handling route names in a structured way
     */
    operator fun invoke() = name.lowercase()

    companion object {
        val Initial = Home
    }
}
