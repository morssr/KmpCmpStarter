import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleTests {

    @Test
    fun testAddition() {
        assertEquals(4, 2 + 2) // Simple assertion
    }

}

internal interface SimpleTestsInterface {
    fun testSubtraction()
}