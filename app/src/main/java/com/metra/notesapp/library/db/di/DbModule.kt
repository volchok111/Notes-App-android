package com.metra.notesapp.library.db.di

import androidx.room.Room
import com.metra.notesapp.library.db.dao.ReminderRepository
import com.metra.notesapp.library.db.data.RemindersDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            RemindersDatabase::class.java,
            "notes_database"
        ).build()
    }

    single { get<RemindersDatabase>().reminderDao() }

    single { ReminderRepository(get()) }
}
