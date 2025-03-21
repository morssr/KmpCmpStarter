package com.kmp.cmp.app.data

import com.kmp.cmp.app.data.common.KtorClientHelper
import com.kmp.cmp.app.data.randomusers.common.randomUsersDataModule
import io.ktor.client.HttpClient
import org.koin.dsl.module

val dataModule = module {
    single<HttpClient> { KtorClientHelper.getClient() }

    includes(
        randomUsersDataModule,
    )
}