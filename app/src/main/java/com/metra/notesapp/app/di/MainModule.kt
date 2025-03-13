package com.metra.notesapp.app.di

import com.metra.notesapp.app.device.GlobalNavigationController
import com.metra.notesapp.app.domain.MainNavigationController
import com.metra.notesapp.app.domain.ObserveNavigationEventsUseCase
import com.metra.notesapp.app.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.binds
import org.koin.dsl.module

internal val mainModule = module {
    viewModelOf(::MainViewModel)
    factory { ObserveNavigationEventsUseCase(get()) }

    single { GlobalNavigationController() }.binds(
        arrayOf(
            MainNavigationController::class
        )
    )
}
