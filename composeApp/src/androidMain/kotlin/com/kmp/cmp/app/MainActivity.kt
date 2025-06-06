package com.kmp.cmp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kmp.cmp.app.presentation.AppUI
import dev.icerock.moko.geo.LocationTracker
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainActivity : ComponentActivity() {

    private val locationTracker: LocationTracker by inject()
    private val locationTrackerLow: LocationTracker by inject(named("low"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationTracker.bind(this)
        locationTrackerLow.bind(this)
        setContent {
            AppUI()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    AppUI()
}