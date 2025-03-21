package com.metra.notesapp.app.model

typealias ForwardNavigationEvent = NavigationEvent.ForwardEvent
typealias BackNavigationEvent = NavigationEvent.BackEvent

/**
 * Represents navigation events within the application.
 *
 * This sealed interface is used to handle different types of navigation actions,
 * ensuring type safety and a structured approach to navigation management
 */

sealed interface NavigationEvent {

    /**
     * @param route The destination route where the navigation should take place
     * @param clearBackStack If `true`, the entire back stack will be cleared before navigating
     * @param clearBackStackRoute If specified, the back stack will be cleared up to this route
     */
    data class ForwardEvent(
        val route: Route,
        val clearBackStack: Boolean = false,
        val clearBackStackRoute: Route? = null
    ) : NavigationEvent

    data object BackEvent : NavigationEvent
}
