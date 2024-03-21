package online.muhammadali.kite.location.domain.models

import online.muhammadali.kite.common.utl.Result

interface KeysProvider {
    fun getPrivateKey(): Result<ByteArray>
    fun getPublicKey(): Result<ByteArray>
}