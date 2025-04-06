package com.metra.notesapp.feature.add.di

import com.metra.notesapp.feature.add.presentation.AddViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val detailsModule = module {
    viewModelOf(::AddViewModel)
}
