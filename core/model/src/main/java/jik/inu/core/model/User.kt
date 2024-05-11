package jik.inu.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val email: String,
    @Json(name = "color") val profileColor: String,
)