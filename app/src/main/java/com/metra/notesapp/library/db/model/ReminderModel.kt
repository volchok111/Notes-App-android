package com.metra.notesapp.library.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "reminders")
data class ReminderModel(
    @PrimaryKey val id: UUID = UUID.randomUUID(), // type can be changed to String in a future
    val title: String,
    val description: String?,
    val dateTime: Long,
    val isCompleted: Boolean = false,
    val priority: PriorityType,
    val repeatType: RepeatType = RepeatType.NONE,
    val createdAt: Long = System.currentTimeMillis() // type can be changed in a future
)
