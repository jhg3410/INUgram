package jik.inu.data.repository.video

import android.net.Uri
import jik.inu.core.model.Video

interface VideoRepository {

    suspend fun upload(contentUri: Uri, description: String): Result<Unit>

    suspend fun getVideos(): Result<List<Video>>

    suspend fun getLikedVideos(): Result<Set<Int>>

    suspend fun like(videoId: Int): Result<Unit>

    suspend fun disLike(videoId: Int): Result<Unit>
}