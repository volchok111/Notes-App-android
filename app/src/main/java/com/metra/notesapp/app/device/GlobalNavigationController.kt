package com.metra.notesapp.app.device

import com.metra.notesapp.app.domain.MainNavigationController
import com.metra.notesapp.app.model.BackNavigationEvent
import com.metra.notesapp.app.model.ForwardNavigationEvent
import com.metra.notesapp.app.model.NavigationEvent
import com.metra.notesapp.app.model.Route
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class GlobalNavigationController :
    MainNavigationController {

    /**
     * Allows one-time events to be observed without losing
     * `[extraBufferCapacity] = 1` means that one extra event is stored if no collector is active (this prevents event loss)
     */
    private val _navigationEvent = MutableSharedFlow<NavigationEvent>(extraBufferCapacity = 1)
    override val navigationEvent = _navigationEvent.asSharedFlow()

    override fun goBack() = goTo(BackNavigationEvent)

    override fun goToHome() = goTo(ForwardNavigationEvent(Route.Home, clearBackStack = true))

    private fun goTo(navigationEvent: NavigationEvent) {
        _navigationEvent.tryEmit(navigationEvent)
    }
}
