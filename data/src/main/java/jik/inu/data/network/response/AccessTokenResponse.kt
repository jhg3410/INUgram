package jik.inu.data.network.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccessTokenResponse(
    val accessToken: String
)