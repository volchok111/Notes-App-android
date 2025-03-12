package com.metra.notesapp.library.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.metra.notesapp.library.db.model.ReminderModel
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ReminderDao {

    @Insert
    suspend fun addReminder(reminder: ReminderModel)

    @Update
    suspend fun updateReminder(reminder: ReminderModel)

    @Delete
    suspend fun deleteReminder(reminder: ReminderModel)

    @Query("SELECT * FROM reminders WHERE id = :id")
    suspend fun getReminderById(id: UUID): ReminderModel?

    @Query("SELECT * FROM reminders ORDER BY datetime ASC")
    fun getAllReminders(): Flow<List<ReminderModel>>

    @Query("SELECT * FROM reminders WHERE isCompleted = 0 ORDER BY datetime ASC")
    fun getPendingReminders(): Flow<List<ReminderModel>>
}
