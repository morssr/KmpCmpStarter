package general.ktor

import com.kmp.cmp.app.data.common.KtorClientHelper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.test.Test

class KtorGeneralTests : KoinTest {

    private val mockEngine = MockEngine { request ->
        respond(
            content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
            status = HttpStatusCode.OK,
            headers = headersOf(
                HttpHeaders.ContentType,
                ContentType.Application.Json.toString()
            )
        )
    }

    @BeforeTest
    fun setup() {
        startKoin {
            modules(ktorTestModule)
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }


    @Test
    fun isolated_client_test() {
        runBlocking {
            val client = HttpClient(mockEngine) {
                install(ContentNegotiation) {
                    json(Json {
                        ignoreUnknownKeys = true
                        coerceInputValues = true
                    })
                }
            }
            val response: IpResponse = client.get(Url("https://api.ipify.org/?format=json")).body()
            assertEquals("isolated request test failed", "127.0.0.1", response.ip)
        }
    }

    @Test
    fun factory_client_test() {
        runBlocking {
            val client = KtorClientHelper.getClient(mockEngine)
            val response: IpResponse = client.get(Url("https://api.ipify.org/?format=json")).body()
            assertEquals("factory request test failed", "127.0.0.1", response.ip)
        }
    }

    @Test
    fun di_injected_client_test() {
        runBlocking {
            val client: HttpClient = get(parameters = { parametersOf(mockEngine) })
            val response: IpResponse = client.get(Url("https://api.ipify.org/?format=json")).body()
            assertEquals("di injected request test failed", "127.0.0.1", response.ip)
        }
    }
}

@Serializable
data class IpResponse(val ip: String)
