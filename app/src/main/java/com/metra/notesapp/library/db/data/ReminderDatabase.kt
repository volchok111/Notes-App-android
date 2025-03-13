package com.metra.notesapp.library.db.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.metra.notesapp.library.db.dao.ReminderDAO
import com.metra.notesapp.library.db.model.Reminder
import com.metra.notesapp.library.utils.Converters

@TypeConverters(Converters::class)
@Database(entities = [Reminder::class], version = 1, exportSchema = false)
abstract class ReminderDatabase : RoomDatabase() {

    abstract fun getReminderDao(): ReminderDAO
}
