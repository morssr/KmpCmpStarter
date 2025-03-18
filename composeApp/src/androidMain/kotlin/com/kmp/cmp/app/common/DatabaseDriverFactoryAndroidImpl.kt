package com.kmp.cmp.app.common

import android.content.Context
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import co.touchlab.kermit.Logger
import com.kmp.cmp.app.data.common.DatabaseDriverFactory

private const val TAG = "DatabaseDriverFactoryAndroidImpl"

class DatabaseDriverFactoryAndroidImpl(
    private val schema: SqlSchema<QueryResult.Value<Unit>>,
    val dbName: String,
    private val context: Context,
    logger: Logger
) : DatabaseDriverFactory {

    private val log = logger.withTag(TAG)

    override fun create(): SqlDriver {
        log.d { "create(): create android db SqlDriver called" }
        return AndroidSqliteDriver(
            schema,
            context,
            dbName
        )
    }
}