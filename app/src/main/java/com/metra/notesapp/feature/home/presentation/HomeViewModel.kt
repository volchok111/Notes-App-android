package com.metra.notesapp.feature.home.presentation

import com.metra.notesapp.library.db.model.ReminderPriority
import com.metra.notesapp.library.db.model.ReminderRepeatType
import com.metra.notesapp.library.mvvm.presentation.AbstractViewModel

class HomeViewModel : AbstractViewModel<HomeViewModel.State>(State()) {

    data class State(
        val reminders: List<ReminderItem> = emptyList()
    ) : AbstractViewModel.State {
        data class ReminderItem(
            val id: String? = null,
            val title: String? = null,
            val description: String? = null,
            val dateTime: Long? = null,
            val isCompleted: Boolean? = null,
            val priority: ReminderPriority? = null,
            val repeatType: ReminderRepeatType = ReminderRepeatType.NONE,
            val color: Int? = null
        )
    }
}
