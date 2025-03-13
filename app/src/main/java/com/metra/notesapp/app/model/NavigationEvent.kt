package com.metra.notesapp.app.model

typealias ForwardNavigationEvent = NavigationEvent.ForwardEvent
typealias BackNavigationEvent = NavigationEvent.BackEvent

sealed interface NavigationEvent {

    data class ForwardEvent(
        val route: Route,
        val clearBackStack: Boolean = false,
        val clearBackStackRoute: Route? = null
    ) : NavigationEvent

    object BackEvent : NavigationEvent
}
