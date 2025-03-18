import app.cash.sqldelight.db.SqlDriver
import com.kmp.cmp.app.Database
import org.koin.core.module.Module
import org.koin.dsl.module

private val commonTestModule = module {
    single { Database(driver = get<SqlDriver>()) }
}

val testModule = module {
    includes(
        commonTestModule,
        platformTestModule
    )
}

expect val platformTestModule: Module