package com.metra.notesapp.feature.add.presentation

import com.metra.notesapp.library.db.model.Reminder
import com.metra.notesapp.library.mvvm.presentation.AbstractViewModel

class AddViewModel: AbstractViewModel<AddViewModel.State>(State()) {



    data class State(
        val reminder: Reminder? = null
    ): AbstractViewModel.State
}