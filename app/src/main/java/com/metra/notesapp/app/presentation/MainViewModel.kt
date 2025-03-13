package com.metra.notesapp.app.presentation

import androidx.lifecycle.viewModelScope
import com.metra.notesapp.app.domain.ObserveNavigationEventsUseCase
import com.metra.notesapp.app.model.ForwardNavigationEvent
import com.metra.notesapp.app.model.NavigationEvent
import com.metra.notesapp.app.model.Route
import com.metra.notesapp.library.mvvm.presentation.AbstractViewModel
import com.metra.notesapp.library.usecase.domain.invoke
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for handling navigation events in the main scope of the application
 *
 * This ViewModel listens for navigation events via [ObserveNavigationEventsUseCase]
 * and updates its state accordingly. It provides a mechanism for UI components
 * to observe and react to navigation changes.
 *
 * @param observeNavigationEventsUseCase Use case that observes navigation events
 */

class MainViewModel(
    private val observeNavigationEventsUseCase: ObserveNavigationEventsUseCase
) : AbstractViewModel<MainViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            observeNavigationEventsUseCase().collect { onNavigationEvent(it) }
        }
    }

    /**
     * This method is used when the UI has already processed the navigation event,
     * preventing it from being handled multiple times
     */
    fun onNavigationEventConsumed() {
        state = state.copy(navigationEvent = null)
    }

    /**
     * Handles a new navigation event and updates the ViewModel state
     */
    private fun onNavigationEvent(navigationEvent: NavigationEvent) {
        state = state.copy(navigationEvent = navigationEvent)
    }

    /**
     * Holds the state for [MainViewModel], including navigation events
     */
    data class State(
        val navigationEvent: NavigationEvent? = ForwardNavigationEvent(Route.Initial)
    ) : AbstractViewModel.State
}
