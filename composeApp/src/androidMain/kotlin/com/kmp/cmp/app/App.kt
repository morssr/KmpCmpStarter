package com.kmp.cmp.app

import android.app.Application
import co.touchlab.kermit.Logger
import co.touchlab.kermit.koin.KermitKoinLogger
import com.kmp.cmp.app.common.DEFAULT_KOIN_TAG
import com.kmp.cmp.app.common.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            logger(KermitKoinLogger(Logger.withTag(DEFAULT_KOIN_TAG)))
            androidLogger()
            modules(appModule)
        }
    }
}