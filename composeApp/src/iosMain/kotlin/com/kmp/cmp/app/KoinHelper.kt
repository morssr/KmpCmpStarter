package com.kmp.cmp.app

import com.kmp.cmp.app.common.appModule
import org.koin.core.context.startKoin

/*
   This is a helper class to initialize Koin
 */
fun initKoin() {
    startKoin {
        modules(appModule)
    }
}