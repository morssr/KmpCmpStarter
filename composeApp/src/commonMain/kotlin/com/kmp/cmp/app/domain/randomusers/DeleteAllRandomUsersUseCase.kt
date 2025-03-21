package com.kmp.cmp.app.domain.randomusers

import co.touchlab.kermit.Logger
import com.kmp.cmp.app.data.randomusers.RandomUsersRepository

private const val TAG = "DeleteAllRandomUsersUse"

class DeleteAllRandomUsersUseCase(
    private val randomUsersRepository: RandomUsersRepository,
    logger: Logger
) {

    private val log = logger.withTag(TAG)

    suspend operator fun invoke() {
        log.d { "invoke(): called" }
        randomUsersRepository.deleteAllUsers()
    }
}