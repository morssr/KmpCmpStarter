package com.kmp.cmp.app.domain.randomusers

import co.touchlab.kermit.Logger
import com.kmp.cmp.app.data.randomusers.RandomUsersRepository
import com.kmp.cmp.app.common.utils.Result

private const val TAG = "FetchNewRandomUserUseCase"

class FetchNewRandomUserUseCase(
    private val randomUsersRepository: RandomUsersRepository,
    logger: Logger
) {
    private val log = logger.withTag(TAG)

    suspend operator fun invoke(): Result<Unit> {
        log.d { "invoke(): called" }
        return randomUsersRepository.fetchNewUser()
    }
}