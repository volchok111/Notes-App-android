package com.metra.notesapp.app.ui

import android.app.Application
import com.metra.notesapp.app.di.mainModule
import com.metra.notesapp.feature.details.di.detailsModule
import com.metra.notesapp.library.db.di.dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        startKoin {
            androidContext(applicationContext)
            modules(
                dbModule,
                detailsModule,
                mainModule
            )
        }
        super.onCreate()
    }
}
