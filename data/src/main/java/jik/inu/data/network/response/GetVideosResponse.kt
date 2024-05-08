package jik.inu.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import jik.inu.core.model.Video

@JsonClass(generateAdapter = true)
data class GetVideosResponse(
    @Json(name = "allVideos") val videos: List<Video>,
    val next: Boolean
)