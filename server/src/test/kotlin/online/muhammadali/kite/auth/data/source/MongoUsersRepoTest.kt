package online.muhammadali.kite.auth.data.source

import com.mongodb.kotlin.client.coroutine.MongoClient
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import online.muhammadali.kite.auth.data.repositories.MongoUsersRepo
import online.muhammadali.kite.auth.domain.models.User
import online.muhammadali.kite.common.utl.Failure
import online.muhammadali.kite.common.utl.Success
import org.bson.types.ObjectId
import org.junit.Test

class MongoUsersRepoTest {

    val db = MongoClient.create("mongodb://localhost:27017").getDatabase("users")
    val repo = MongoUsersRepo(UsersDb(db))

    @Test
    fun `success if add new user`() {
        runBlocking {
            val id = ObjectId().toString()

            val newUser = User(
                id = id,
                name = "testName",
                email = "testEmail"
            )

            repo.addNewUser(newUser)

            val result = repo.getUser(id).first()

            when (result) {
                is Success -> assert(result.data == newUser)

                is Failure -> assert(false)
            }

            repo.deleteUser(newUser)
        }
    }

    @Test
    fun `success if updates user`() {
        runBlocking{
            val id = ObjectId().toString()

            val newUser = User(
                id = id,
                name = "testName",
                email = "testEmail"
            )

            repo.addNewUser(newUser)
            val updatedUser = newUser.copy(name = "updatedTestName")
            repo.updateUser(updatedUser)

            when (val result = repo.getUser(id).first()) {
                is Success -> assert(result.data == updatedUser)

                is Failure -> assert(false)
            }

            repo.deleteUser(updatedUser)
        }
    }

    @Test
    fun `success if delete user`() {
        runBlocking{
            val id = ObjectId().toString()

            val newUser = User(
                id = id,
                name = "testName",
                email = "testEmail"
            )

            repo.addNewUser(newUser)
            repo.deleteUser(newUser)

            val result = repo.getUser(id).first()
            if (result is Failure)
                assert(true)
            else
                assert(false)
        }
    }
}