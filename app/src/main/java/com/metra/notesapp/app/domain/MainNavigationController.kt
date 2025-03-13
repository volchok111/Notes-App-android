package com.metra.notesapp.app.domain

import com.metra.notesapp.app.model.NavigationEvent
import kotlinx.coroutines.flow.Flow

/**
 * This interface provides a contract for navigating between different screens
 * and handling back navigation. It exposes a `Flow` of `NavigationEvent` ( [Flow]that emits navigation events )
 * that can be observed for real-time navigation updates.
 */
interface MainNavigationController {
    val navigationEvent: Flow<NavigationEvent>

    fun goBack()

    fun goToHome()
}
