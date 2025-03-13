package com.metra.notesapp.library.db.di

import androidx.room.Room
import com.metra.notesapp.library.db.dao.ReminderRepository
import com.metra.notesapp.library.db.data.ReminderDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ReminderDatabase::class.java,
            "reminder_db"
        ).build()
    }

    single { get<ReminderDatabase>().getReminderDao() }

    single { ReminderRepository(get()) }
}
