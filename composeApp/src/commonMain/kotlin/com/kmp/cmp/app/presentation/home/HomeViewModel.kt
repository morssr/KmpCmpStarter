package com.kmp.cmp.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import dev.icerock.moko.geo.LocationTracker
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.location.BACKGROUND_LOCATION
import dev.icerock.moko.permissions.location.COARSE_LOCATION
import dev.icerock.moko.permissions.location.LOCATION
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

private const val TAG = "HomeViewModel"

class HomeViewModel(
    private val locationTracker: LocationTracker,
    private val locationTrackerLow: LocationTracker,
    logger: Logger
) : ViewModel() {

    private val log = logger.withTag(TAG)
    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        log.d { "init() called" }
//        viewModelScope.launch {
//            locationTracker.startTracking()
//
//        }

        viewModelScope.launch {
            val p = locationTracker.permissionsController.getPermissionState(Permission.LOCATION)
            val pc =
                locationTracker.permissionsController.getPermissionState(Permission.COARSE_LOCATION)
            val pb =
                locationTracker.permissionsController.getPermissionState(Permission.BACKGROUND_LOCATION)

            locationTracker.permissionsController.providePermission(Permission.LOCATION)
            log.i { "permission location: $p" }
            log.i { "permission coarse location: $pc" }
            log.i { "permission background location: $pb" }
        }

        locationTracker.getLocationsFlow()
            .onStart { locationTracker.startTracking() }
//            .distinctUntilChanged()
            .onEach { location ->
                log.d { "Location1: $location" }
                _uiState.update {
                    it.copy(
                        locationsList = it.locationsList + LocationState(
                            latitude = location.latitude,
                            longitude = location.longitude,
                            time = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time.toString()
                        )
                    )
                }
            }
            .launchIn(viewModelScope)

//        locationTracker.getLocationsFlow()
//            .distinctUntilChanged()
//            .onEach { location ->
//                log.d { "Location2: $location" }
//                _uiState.update {
//                    it.copy(
//                        location = "${location.latitude}, ${location.longitude}"
//                    )
//                }
//            }
//            .launchIn(viewModelScope)
//
//        viewModelScope.launch {
//            locationTrackerLow.startTracking()
//            delay(10.seconds)
//            log.d { "Location3: get last loc simulation" }
//            val loc = locationTrackerLow.getLocationsFlow()
//                .onEach { log.d { "Location3: $it" } }
//                .first()
//
//            log.d { "Location3 res: $loc" }
//        }
    }

    override fun onCleared() {
        super.onCleared()
        log.d { "onCleared() called" }
//        locationTracker.stopTracking()
    }
}

data class HomeScreenState(
    val placeholderString: String = "Home Screen",
    val locationsList: List<LocationState> = emptyList()
)

data class LocationState(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val time: String
)