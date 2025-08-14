package com.kmp.cmp.app.presentation.home

import androidx.lifecycle.ViewModel
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val TAG = "HomeViewModel"

class HomeViewModel(
    logger: Logger
) : ViewModel() {

    private val log = logger.withTag(TAG)
    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        log.d { "init() called" }
    }

    override fun onCleared() {
        super.onCleared()
        log.d { "onCleared() called" }
//        locationTracker.stopTracking()
    }
}

data class HomeScreenState(
    val placeholderString: String = "Home Screen",
)