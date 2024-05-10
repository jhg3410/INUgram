package jik.inu.data.util

import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import retrofit2.HttpException

internal inline fun <T, R> T.jikCatching(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        val exception =
            if (e is HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                if (errorBody == null) {
                    return Result.failure(Throwable(message = DEFAULT_ERROR_MESSAGE))
                } else {
                    val errorResponse = fromJsonToErrorResponse(errorBody)
                    Throwable(message = errorResponse.message)
                }
            } else {
                e
            }
        Result.failure(exception)
    }
}


private const val DEFAULT_ERROR_MESSAGE = "INUgram 에서 문제가 발생했어요"

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "errorMsg") val message: String
)

@OptIn(ExperimentalStdlibApi::class)
fun fromJsonToErrorResponse(json: String): ErrorResponse {
    val moshi: Moshi = Moshi.Builder().build()
    val jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter<ErrorResponse>()
    return jsonAdapter.fromJson(json) ?: ErrorResponse(DEFAULT_ERROR_MESSAGE)
}