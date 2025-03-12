package com.metra.notesapp.library.db.dao

import com.metra.notesapp.library.db.model.ReminderModel
import java.util.UUID

class ReminderRepository(
    private val reminderDao: ReminderDao
) {

    suspend fun addReminder(reminder: ReminderModel) = reminderDao.addReminder(reminder)

    suspend fun updateReminder(reminder: ReminderModel) = reminderDao.updateReminder(reminder)

    suspend fun deleteReminder(reminder: ReminderModel) = reminderDao.deleteReminder(reminder)

    suspend fun getReminderById(id: UUID) = reminderDao.getReminderById(id)

    fun getAllReminders() = reminderDao.getAllReminders()

    fun getPendingReminders() = reminderDao.getPendingReminders()
}
