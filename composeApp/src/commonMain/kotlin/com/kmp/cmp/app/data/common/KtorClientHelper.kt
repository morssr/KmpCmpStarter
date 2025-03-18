package com.kmp.cmp.app.data.common

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClientHelper {

    fun getClient(engine: HttpClientEngine? = null): HttpClient {
        val client = if (engine != null) {
            HttpClient(engine = engine)
        } else {
            HttpClient()
        }
        val clientWithConfiguration = configHttpClient(client)
        return clientWithConfiguration
    }

    private fun configHttpClient(client: HttpClient) : HttpClient {
        return client.config {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                })
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.BODY
            }
        }
    }
}