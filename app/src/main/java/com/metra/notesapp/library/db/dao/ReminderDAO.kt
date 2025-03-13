package com.metra.notesapp.library.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.metra.notesapp.library.db.model.Reminder
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ReminderDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: Reminder)

    @Update
    suspend fun updateReminder(reminder: Reminder)

    @Delete
    suspend fun deleteReminder(reminder: Reminder)

    @Query("SELECT * FROM reminders WHERE id = :id")
    suspend fun getReminderById(id: UUID): Reminder?

    @Query("SELECT * FROM REMINDERS ORDER BY datetime ASC")
    fun getAllReminders(): Flow<List<Reminder>>
}
