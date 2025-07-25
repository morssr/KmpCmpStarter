package com.kmp.cmp.app.presentation

import com.kmp.cmp.app.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        HomeViewModel(
            locationTracker = get(),
            locationTrackerLow = get(named("low")),
            logger = get()
        )
    }
}