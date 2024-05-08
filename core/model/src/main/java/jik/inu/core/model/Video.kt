package jik.inu.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Video(
    @Json(name = "videoUrl") val url: String,
    @Json(name = "thumbnailUrl") val thumbnail: String,
    @Json(name = "title") val description: String,
)