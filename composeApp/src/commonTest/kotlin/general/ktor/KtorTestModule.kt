package general.ktor

import com.kmp.cmp.app.data.common.KtorClientHelper
import io.ktor.client.HttpClient
import org.koin.dsl.module

val ktorTestModule = module {
    single<HttpClient> { KtorClientHelper.getClient(engine = it.get()) }
}