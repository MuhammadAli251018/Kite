package online.muhammadali.kite.auth.data.source

import com.mongodb.kotlin.client.coroutine.MongoClient
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import online.muhammadali.kite.auth.data.enitities.toUserEntity
import online.muhammadali.kite.auth.data.repositories.MongoUsersRepo
import online.muhammadali.kite.common.domain.User
import online.muhammadali.kite.common.utl.Failure
import online.muhammadali.kite.common.utl.Success
import org.bson.types.ObjectId
import org.junit.Test

class MongoUsersRepoTest {

    val db = MongoClient.create("mongodb://localhost:27017").getDatabase("users")
    val repo = MongoUsersRepo(UsersDb(db))

    @Test
    fun `success if converts user to user entity`() {
        val id = ObjectId().toString()

        val user = User(
            id = id,
            name = "testName",
            email = "testEmail"
        )

        val userEntity = user.toUserEntity()

        assert(ObjectId(user.id) == userEntity._id)
    }

    @Test
    fun `success if add new user`() {
        runBlocking {
            val id = ObjectId().toString()

            val newUser = User(
                id = id,
                name = "testName",
                email = "testEmail"
            )

            val addingResult = repo.addNewUser(newUser).first()

            val result = repo.getUser(id).first()

            println("adding id: ${id}")
            println(result)

            when (result) {
                is Success -> assert(result.data == newUser)

                is Failure -> assert(false)
            }

            repo.deleteUser(newUser).first()
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

            repo.addNewUser(newUser).first()
            val updatedUser = newUser.copy(name = "updatedTestName")
            repo.updateUser(updatedUser).first()

            when (val result = repo.getUser(id).first()) {
                is Success -> assert(result.data == updatedUser)

                is Failure -> assert(false)
            }

            repo.deleteUser(updatedUser).first()
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

            repo.addNewUser(newUser).first()
            repo.deleteUser(newUser).first()

            val result = repo.getUser(id).first()
            if (result is Failure)
                assert(true)
            else
                assert(false)
        }
    }
}