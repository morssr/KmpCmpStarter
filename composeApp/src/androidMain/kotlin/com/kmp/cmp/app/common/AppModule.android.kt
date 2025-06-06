package com.kmp.cmp.app.common

import com.google.android.gms.location.LocationRequest
import com.kmp.cmp.app.Database
import com.kmp.cmp.app.data.common.DataConstants
import com.kmp.cmp.app.data.common.DatabaseDriverFactory
import dev.icerock.moko.geo.LocationTracker
import dev.icerock.moko.permissions.PermissionsController
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual val platformModule = module {

    single<DatabaseDriverFactory> {
        DatabaseDriverFactoryAndroidImpl(
            schema = Database.Schema,
            dbName = DataConstants.APP_DB_NAME,
            context = androidContext(),
            logger = get()
        )
    }

    single<LocationTracker> {
        LocationTracker(
            PermissionsController(
                applicationContext = androidContext()
            )
        )
    }

    single<LocationTracker>(named("low")) {
        LocationTracker(
            PermissionsController(applicationContext = androidContext()),
            interval = 5,
            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        )
    }
}