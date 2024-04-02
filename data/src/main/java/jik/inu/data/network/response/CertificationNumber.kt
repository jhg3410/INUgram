package jik.inu.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CertificationNumber(
    @Json(name = "certificateNumber") val number: String
)