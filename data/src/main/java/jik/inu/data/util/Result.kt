package jik.inu.data.util

import retrofit2.HttpException

internal inline fun <T, R> T.jikCatching(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        val exception =
            if (e is HttpException) {
                Throwable(message = e.response()?.errorBody()?.string())
            } else {
                e
            }
        Result.failure(exception)
    }
}