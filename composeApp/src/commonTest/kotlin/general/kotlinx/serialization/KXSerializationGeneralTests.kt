package general.kotlinx.serialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class KXSerializationGeneralTests {

    @Test
    fun simple_serialization_test() {
        val data = SimpleData(42, "foo")
        val json = Json.encodeToString(SimpleData.serializer(), data)
        val parsedData = Json.decodeFromString(SimpleData.serializer(), json)
        assertEquals("""{"a":42,"b":"foo"}""", json)
        assertEquals(data, parsedData)
    }
}

@Serializable
data class SimpleData(val a: Int, val b: String)