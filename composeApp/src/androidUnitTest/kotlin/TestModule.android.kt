import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.kmp.cmp.app.Database
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformTestModule: Module = module {
    single<SqlDriver> {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        Database.Schema.create(driver)
        driver
    }
}