package com.kmp.cmp.app.domain

import com.kmp.cmp.app.domain.randomusers.DeleteAllRandomUsersUseCase
import com.kmp.cmp.app.domain.randomusers.FetchNewRandomUserUseCase
import com.kmp.cmp.app.domain.randomusers.GetAllRandomUsersUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<FetchNewRandomUserUseCase> {
        FetchNewRandomUserUseCase(
            randomUsersRepository = get(),
            logger = get()
        )
    }

    factory<GetAllRandomUsersUseCase> {
        GetAllRandomUsersUseCase(
            randomUsersRepository = get(),
            logger = get()
        )
    }

    factory<DeleteAllRandomUsersUseCase> {
        DeleteAllRandomUsersUseCase(
            randomUsersRepository = get(),
            logger = get()
        )
    }
}