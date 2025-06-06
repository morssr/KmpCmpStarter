package com.kmp.cmp.app.common

import com.kmp.cmp.app.Database
import com.kmp.cmp.app.data.common.DataConstants
import com.kmp.cmp.app.data.common.DatabaseDriverFactory
import dev.icerock.moko.geo.LocationTracker
import dev.icerock.moko.permissions.ios.PermissionsController
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import platform.CoreLocation.kCLLocationAccuracyBest
import platform.CoreLocation.kCLLocationAccuracyNearestTenMeters

actual val platformModule: Module = module {
    single<DatabaseDriverFactory> {
        DatabaseDriverFactoryIosImpl(
            schema = Database.Schema,
            dbName = DataConstants.APP_DB_NAME,
            logger = get()
        )
    }

    single<LocationTracker> {
        LocationTracker(
            permissionsController = PermissionsController(),
            accuracy = kCLLocationAccuracyBest
        )
    }

    single<LocationTracker>(named("low")) {
        LocationTracker(
            permissionsController = PermissionsController(),
            accuracy = kCLLocationAccuracyNearestTenMeters
        )
    }
}