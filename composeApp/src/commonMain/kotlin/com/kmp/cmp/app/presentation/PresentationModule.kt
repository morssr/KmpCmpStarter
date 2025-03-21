package com.kmp.cmp.app.presentation

import com.kmp.cmp.app.presentation.home.HomeViewModel
import com.kmp.cmp.app.presentation.randomusers.RandomUsersViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        HomeViewModel(
            logger = get()
        )
    }

    viewModel {
        RandomUsersViewModel(
            fetchNewRandomUserUseCase = get(),
            getAllRandomUsersUseCase = get(),
            deleteAllRandomUsersUseCase = get(),
            logger = get()
        )
    }
}