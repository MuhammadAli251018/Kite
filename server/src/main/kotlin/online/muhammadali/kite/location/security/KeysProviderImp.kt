package online.muhammadali.kite.location.security

import io.ktor.utils.io.errors.IOException
import online.muhammadali.kite.common.utl.Failure
import online.muhammadali.kite.common.utl.Result
import online.muhammadali.kite.common.utl.Success
import online.muhammadali.kite.location.domain.models.KeysProvider
import java.io.File
import java.util.Base64

private const val PRIVATE_KEY_FILE_NAME = "private_key"
private const val PUBLIC_KEY_FILE_NAME = "public_key"

class KeysProviderImp(private val keysPath: String) : KeysProvider {
    private fun readKey(fileName: String): Result<ByteArray> {
        return try{
            val base64Key = File("$keysPath/$PRIVATE_KEY_FILE_NAME").readText()
            Success(Base64.getDecoder().decode(base64Key))
        } catch (e: IOException) {
            Failure(e)
        }
    }
    override fun getPrivateKey(): Result<ByteArray> = readKey(fileName = "$keysPath/$PRIVATE_KEY_FILE_NAME")
    override fun getPublicKey(): Result<ByteArray> = readKey(fileName = "$keysPath/$PUBLIC_KEY_FILE_NAME")
}