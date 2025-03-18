import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.kmp.cmp.app.Database
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformTestModule: Module = module {
    single {
        NativeSqliteDriver(
            schema = Database.Schema,
            name = "test.db",
            onConfiguration = {
                it.copy(inMemory = true)
            }
        )
    }
}