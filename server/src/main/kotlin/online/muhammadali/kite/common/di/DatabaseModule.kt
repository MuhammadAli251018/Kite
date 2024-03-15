package online.muhammadali.kite.common.di

import com.mongodb.kotlin.client.coroutine.MongoClient
import online.muhammadali.kite.auth.data.repositories.MongoUsersRepo
import online.muhammadali.kite.auth.data.source.UsersDb
import online.muhammadali.kite.auth.data.util.security.JwtGenerator
import online.muhammadali.kite.auth.domain.repositories.UsersDBRepo

object DatabaseModule {
    private val connectionString = ""

    val usersDb: UsersDb by lazy { UsersDb(
        MongoClient.create(connectionString).getDatabase("users")
    ) }

    val usersDBRepo: UsersDBRepo
        get() = MongoUsersRepo(usersDb)
}