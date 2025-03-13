package com.metra.notesapp.library.db.dao

import com.metra.notesapp.library.db.model.Reminder
import java.util.UUID

class ReminderRepository(private val reminderDao: ReminderDAO) {

    suspend fun insertReminder(reminder: Reminder) = reminderDao.insertReminder(reminder)
    suspend fun updateReminder(reminder: Reminder) = reminderDao.updateReminder(reminder)
    suspend fun deleteReminder(reminder: Reminder) = reminderDao.deleteReminder(reminder)
    suspend fun getReminderById(id: UUID) = reminderDao.getReminderById(id)

    fun getAllReminders() = reminderDao.getAllReminders()
}
