package com.kmp.cmp.app.common

import com.kmp.cmp.app.Database
import com.kmp.cmp.app.data.common.DataConstants
import com.kmp.cmp.app.data.common.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<DatabaseDriverFactory> {
        DatabaseDriverFactoryIosImpl(
            schema = Database.Schema,
            dbName = DataConstants.APP_DB_NAME,
            logger = get()
        )
    }
}