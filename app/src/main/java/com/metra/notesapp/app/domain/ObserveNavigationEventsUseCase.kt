package com.metra.notesapp.app.domain

import com.metra.notesapp.app.model.NavigationEvent
import com.metra.notesapp.library.usecase.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

class ObserveNavigationEventsUseCase(
    private val mainNavigationController: MainNavigationController
) : SynchronousUseCase<Unit, Flow<NavigationEvent>> {
    override fun invoke(input: Unit): Flow<NavigationEvent> = mainNavigationController.navigationEvent
}
