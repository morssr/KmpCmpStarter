package com.kmp.cmp.app.data.randomusers.storage

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import co.touchlab.kermit.Logger
import com.kmp.cmp.app.Database
import com.kmp.cmp.app.data.randomusers.common.dbModelToEntity
import com.kmp.cmp.app.data.randomusers.common.toDbModel
import com.kmp.cmp.app.data.randomusers.common.toRandomUsers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

private const val TAG = "UsersDaoImpl"

class RandomUsersDaoImpl(
    db: Database,
    private val coroutineContext: CoroutineContext = Dispatchers.IO,
    logger: Logger
) : RandomUsersDao {

    private val log = logger.withTag(TAG)
    private val usersDb = db.randomUserQueries

    override fun getRandomUsersStream(): Flow<List<RandomUserEntity>> {
        log.d { "getUsersStream() called" }
        return usersDb.selectAllRandomUsers()
            .asFlow()
            .mapToList(coroutineContext)
            .map { it.toRandomUsers() }
    }

    override suspend fun insertRandomUsers(users: List<RandomUserEntity>) {
        log.d { "insertUser() called with users: $users" }
        usersDb.transaction {
            users.forEach {
                usersDb.insertRandomUser(it.toDbModel())
            }
        }
    }

    override suspend fun getRandomUserById(id: String): RandomUserEntity? {
        log.d { "getUserById() called with id: $id" }
        val user = usersDb.selectRandomUserById(id).executeAsOneOrNull()
        return user?.dbModelToEntity()
    }

    override suspend fun deleteAllRandomUsers() {
        log.d { "deleteAllRandomUsers() called" }
        usersDb.deleteAllRandomUsers()
    }
}