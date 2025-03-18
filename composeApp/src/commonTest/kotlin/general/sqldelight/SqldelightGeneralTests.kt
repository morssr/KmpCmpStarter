package general.sqldelight

import com.kmp.cmp.app.Database
import com.kmp.cmp.app.Placeholder_test
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import testModule
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

//TODO: Test fails on ios, investigate why.
class SqldelightGeneralTests : KoinTest {

    @BeforeTest
    fun setup() {
        startKoin {
            modules(testModule)
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun validate_db_creation() = runTest {
        println("Validating db creation")
        val database: Database = get()
        assertNotNull(database)
    }

    @Test
    fun check_write_read() = runTest {
        val database: Database = get()
        database.placeholder_testQueries.insert(Placeholder_test(1, "Hello"))
        val all = database.placeholder_testQueries.selectAll().executeAsList()
        assertEquals(1, all.size)
    }
}