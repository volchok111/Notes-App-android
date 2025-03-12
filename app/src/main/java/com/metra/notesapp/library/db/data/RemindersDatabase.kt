package com.metra.notesapp.library.db.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.metra.notesapp.library.db.dao.ReminderDao
import com.metra.notesapp.library.db.model.ReminderModel
import com.metra.notesapp.library.utils.Converters

@Database(entities = [ReminderModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RemindersDatabase : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao
}
