package jik.inu.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import jik.inu.lib.videoplayer.shorts.ShortsVideo

@JsonClass(generateAdapter = true)
data class Video(
    @Json(name = "videoId") val id: Int,
    @Json(name = "videoUrl") val url: String,
    @Json(name = "thumbnailUrl") val thumbnail: String,
    @Json(name = "title") val description: String
) {
    fun toShortsVideo(): ShortsVideo =
        ShortsVideo(
            id = id,
            videoUrl = url,
            thumbnailUrl = thumbnail,
            description = description
        )
}