package com.kmp.cmp.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import dev.icerock.moko.geo.LocationTracker
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

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

        locationTracker.getLocationsFlow()
            .onStart { locationTracker.startTracking() }
            .distinctUntilChanged()
            .onEach { location ->
                log.d { "Location1: $location" }
                _uiState.update {
                    it.copy(
                        location = "${location.latitude}, ${location.longitude}"
                    )
                }
            }
            .launchIn(viewModelScope)

        locationTracker.getLocationsFlow()
            .distinctUntilChanged()
            .onEach { location ->
                log.d { "Location2: $location" }
                _uiState.update {
                    it.copy(
                        location = "${location.latitude}, ${location.longitude}"
                    )
                }
            }
            .launchIn(viewModelScope)

        viewModelScope.launch {
            locationTrackerLow.startTracking()
            delay(10.seconds)
            log.d { "Location3: get last loc simulation" }
            val loc = locationTrackerLow.getLocationsFlow()
                .onEach { log.d { "Location3: $it" } }
                .first()

            log.d { "Location3 res: $loc" }
        }
    }

    override fun onCleared() {
        super.onCleared()
        log.d { "onCleared() called" }
//        locationTracker.stopTracking()
    }
}

data class HomeScreenState(
    val placeholderString: String = "Home Screen",
    val location: String = "Location Invalid",
)