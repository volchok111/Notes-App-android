package com.metra.notesapp.app.presentation

import androidx.lifecycle.viewModelScope
import com.metra.notesapp.app.domain.ObserveNavigationEventsUseCase
import com.metra.notesapp.app.model.ForwardNavigationEvent
import com.metra.notesapp.app.model.NavigationEvent
import com.metra.notesapp.app.model.Route
import com.metra.notesapp.library.mvvm.presentation.AbstractViewModel
import com.metra.notesapp.library.usecase.domain.invoke
import kotlinx.coroutines.launch

class MainViewModel(
    private val observeNavigationEventsUseCase: ObserveNavigationEventsUseCase
) : AbstractViewModel<MainViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            observeNavigationEventsUseCase().collect { onNavigationEvent(it) }
        }
    }

    fun onNavigationEventConsumed() {
        state = state.copy(navigationEvent = null)
    }

    private fun onNavigationEvent(navigationEvent: NavigationEvent) {
        state = state.copy(navigationEvent = navigationEvent)
    }

    data class State(
        val navigationEvent: NavigationEvent? = ForwardNavigationEvent(Route.Initial)
    ) : AbstractViewModel.State
}
