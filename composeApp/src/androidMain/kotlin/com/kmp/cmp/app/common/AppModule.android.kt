package com.kmp.cmp.app.common

import com.kmp.cmp.app.Database
import com.kmp.cmp.app.data.common.DataConstants
import com.kmp.cmp.app.data.common.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {

    single<DatabaseDriverFactory> {
        DatabaseDriverFactoryAndroidImpl(
            schema = Database.Schema,
            dbName = DataConstants.APP_DB_NAME,
            context = androidContext(),
            logger = get()
        )
    }
}