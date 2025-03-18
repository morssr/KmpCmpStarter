package com.kmp.cmp.app.common

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import com.kmp.cmp.app.Database
import com.kmp.cmp.app.data.common.DatabaseDriverFactory
import com.kmp.cmp.app.domain.domainModule
import com.kmp.cmp.app.presentation.presentationModule
import com.kmp.cmp.app.data.dataModule
import org.koin.core.module.Module
import org.koin.dsl.module

private val commonModule = module {
    factory {
        Logger(
            config = StaticConfig(
                logWriterList = listOf(platformLogWriter()),
                minSeverity = Severity.Verbose
            ), DEFAULT_APP_TAG
        )
    }

    /**
     * REQUIRED: Plugin IDE installation: https://plugins.jetbrains.com/plugin/8191-sqldelight
     */
    single {
        Database(driver = get<DatabaseDriverFactory>().create())
    }
}

val appModule = module {
    includes(
        commonModule,
        platformModule,
        dataModule,
        domainModule,
        presentationModule,
    )
}

expect val platformModule: Module