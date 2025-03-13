package com.metra.notesapp.app.domain

import com.metra.notesapp.app.model.NavigationEvent
import kotlinx.coroutines.flow.Flow

interface MainNavigationController {
    val navigationEvent: Flow<NavigationEvent>

    fun goBack()

    fun goToHome()
}
