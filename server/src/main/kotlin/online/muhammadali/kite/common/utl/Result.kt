package online.muhammadali.kite.common.utl

sealed interface Result<T>

data class Success<T> (val data: T) : Result<T>

data class Failure<T> (val throwable: Throwable) : Result<T>


fun <T> Result<T>.onHandleResult(onSuccess: (T) -> Unit, onFailure: (Throwable) -> Unit) {
    when(this) {
        is Success -> onSuccess(data)
        is Failure -> onFailure(throwable)
    }
}

fun <T> Result<T>.getOrThrow(): T {
    return when(this) {
        is Success -> data
        is Failure -> throw throwable
    }
}