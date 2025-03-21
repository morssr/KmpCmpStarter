package com.kmp.cmp.app.data.randomusers.common

import com.kmp.cmp.app.data.randomusers.RandomUsersRepository
import com.kmp.cmp.app.data.randomusers.RandomUsersRepositoryImpl
import com.kmp.cmp.app.data.randomusers.api.RandomUsersApi
import com.kmp.cmp.app.data.randomusers.api.RandomUsersApiImpl
import com.kmp.cmp.app.data.randomusers.storage.RandomUsersDao
import com.kmp.cmp.app.data.randomusers.storage.RandomUsersDaoImpl
import org.koin.dsl.module

val randomUsersDataModule = module {
    single<RandomUsersApi> {
        RandomUsersApiImpl(
            client = get(),
            logger = get()
        )
    }

    single<RandomUsersDao> {
        RandomUsersDaoImpl(
            db = get(),
            logger = get()
        )
    }

    single<RandomUsersRepository> {
        RandomUsersRepositoryImpl(
            api = get(),
            dao = get(),
            logger = get()
        )
    }
}