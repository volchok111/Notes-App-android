package com.metra.notesapp.library.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "reminders")
data class Reminder(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String?,
    val dateTime: Long,
    val isCompleted: Boolean = false,
    val priority: ReminderPriority,
    val repeatType: ReminderRepeatType = ReminderRepeatType.NONE,
    val color: Int,
    val createdAt: Long = System.currentTimeMillis()
)
