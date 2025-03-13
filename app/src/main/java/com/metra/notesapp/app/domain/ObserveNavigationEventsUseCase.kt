package com.metra.notesapp.app.domain

import com.metra.notesapp.app.model.NavigationEvent
import com.metra.notesapp.library.usecase.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Use case responsible for observing navigation events from the `MainNavigationController`
 *
 * This use case allows collecting navigation events as a `Flow`, ensuring
 * that navigation changes can be observed in a structured manner
 */

class ObserveNavigationEventsUseCase(
    private val mainNavigationController: MainNavigationController
) : SynchronousUseCase<Unit, Flow<NavigationEvent>> {
    override fun invoke(input: Unit): Flow<NavigationEvent> = mainNavigationController.navigationEvent
}
