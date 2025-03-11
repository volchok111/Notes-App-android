package com.metra.notesapp.library.mvvm.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class AbstractViewModel<S : AbstractViewModel.State>(initialState: S) : ViewModel() {

    interface State

    private val mutableStates = MutableStateFlow(initialState)

    val states: StateFlow<S> = mutableStates.asStateFlow()

    protected var state: S
        get() = mutableStates.value
        set(value) {
            mutableStates.value = value
        }
}
