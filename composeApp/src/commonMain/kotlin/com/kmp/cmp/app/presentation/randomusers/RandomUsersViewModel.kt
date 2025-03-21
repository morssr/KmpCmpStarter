package com.kmp.cmp.app.presentation.randomusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.kmp.cmp.app.common.utils.Result
import com.kmp.cmp.app.domain.randomusers.DeleteAllRandomUsersUseCase
import com.kmp.cmp.app.domain.randomusers.FetchNewRandomUserUseCase
import com.kmp.cmp.app.domain.randomusers.GetAllRandomUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


private const val TAG = "RandomUsersViewModel"

class RandomUsersViewModel(
    private val fetchNewRandomUserUseCase: FetchNewRandomUserUseCase,
    private val getAllRandomUsersUseCase: GetAllRandomUsersUseCase,
    private val deleteAllRandomUsersUseCase: DeleteAllRandomUsersUseCase,
    logger: Logger
) : ViewModel() {

    private val log = logger.withTag(TAG)

    //private state
    private val _uiState = MutableStateFlow(RandomUsersScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        subscribeForRandomUsersStream()
    }

    fun fetchNewRandomUser() {
        log.d { "fetchNewRandomUser(): called" }
        viewModelScope.launch {
            updateState(_uiState.value.copy(isFetchingNewUser = true))
            if (fetchNewRandomUserUseCase.invoke() is Result.Failure) {
                updateState(_uiState.value.copy(fetchingErrorMessage = "Failed to fetch new user"))
            }
            updateState(_uiState.value.copy(isFetchingNewUser = false))
        }
    }

    fun deleteAllRandomUsers() {
        log.d { "deleteAllRandomUsers(): called" }
        viewModelScope.launch {
            deleteAllRandomUsersUseCase.invoke()
        }
    }

    fun clearErrorMessage() {
        log.d { "clearErrorMessage(): called" }
        updateState(_uiState.value.copy(fetchingErrorMessage = ""))
    }

    private fun subscribeForRandomUsersStream() {
        log.d { "subscribeForRandomUsersStream() called" }
        getAllRandomUsersUseCase()
            .onStart {
                updateState(RandomUsersScreenState(initialLoadState = InitialLoadState.Loading))
            }
            .map { users -> users.map { it.toUiModel() } }
            .onEach {
                updateState(
                    uiState.value.copy(
                        users = it,
                        initialLoadState = InitialLoadState.Success
                    )
                )
            }
            .catch { error ->
                updateState(
                    uiState.value.copy(
                        initialLoadState = InitialLoadState.Error(error.message ?: "Unknown error")
                    )
                )
            }
            .launchIn(viewModelScope)
    }

    private fun updateState(newState: RandomUsersScreenState) {
        log.d { "updateState(): newState=$newState" }
        _uiState.value = newState
    }

}

data class RandomUsersScreenState(
    val users: List<RandomUserUiModel> = emptyList(),
    val isFetchingNewUser: Boolean = false,
    val fetchingErrorMessage: String = "",
    val initialLoadState: InitialLoadState = InitialLoadState.Loading
)

sealed class InitialLoadState {
    object Loading : InitialLoadState()
    object Success : InitialLoadState()
    data class Error(val message: String) : InitialLoadState()
}