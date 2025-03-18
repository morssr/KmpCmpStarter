package general.koin

import org.koin.dsl.module

val koinTestModule = module {
    single { "Hello Koin" }
}