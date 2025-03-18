package com.kmp.cmp.app.common

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.kermit.Logger
import com.kmp.cmp.app.data.common.DatabaseDriverFactory

private const val TAG = "DatabaseDriverFactoryIosImpl"

class DatabaseDriverFactoryIosImpl(
    private val schema: SqlSchema<QueryResult.Value<Unit>>,
    private val dbName: String,
    logger: Logger
) : DatabaseDriverFactory {

    private val log = logger.withTag(TAG)

    override fun create(): SqlDriver {
        log.d { "create(): create iOS db SqlDriver called" }
        return NativeSqliteDriver(schema, dbName)
    }
}