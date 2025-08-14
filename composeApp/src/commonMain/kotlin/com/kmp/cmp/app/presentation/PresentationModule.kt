package com.kmp.cmp.app.presentation

import com.kmp.cmp.app.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        HomeViewModel(
            logger = get()
        )
    }
}