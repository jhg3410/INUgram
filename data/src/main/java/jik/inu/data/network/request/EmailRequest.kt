package jik.inu.data.network.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmailRequest(
    val email: String
)
