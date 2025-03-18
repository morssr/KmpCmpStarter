package general.koin

import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class KoinDIGeneralTests : KoinTest {

    @BeforeTest
    fun setup() {
        startKoin {
            modules(koinTestModule)
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun simple_injection_test() {
        val helloKoin: String = get()
        assertEquals("Hello Koin", helloKoin)
    }
}