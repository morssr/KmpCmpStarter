package com.kmp.cmp.app.data

import com.kmp.cmp.app.data.common.KtorClientHelper
import io.ktor.client.HttpClient
import org.koin.dsl.module

val dataModule = module {
    single<HttpClient> { KtorClientHelper.getClient() }
}