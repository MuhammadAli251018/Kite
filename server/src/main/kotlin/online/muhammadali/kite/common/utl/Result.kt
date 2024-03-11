package online.muhammadali.kite.common.utl

sealed interface Result<T>

data class Success<T> (val data: T) : Result<T>

data class Failure<T> (val throwable: Throwable) : Result<T>

