package jik.inu.data.repository.video

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import jik.inu.core.model.Video
import jik.inu.data.network.service.VideoService
import jik.inu.data.util.jikCatching
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val videoService: VideoService
) : VideoRepository {

    override suspend fun upload(
        contentUri: Uri,
        description: String
    ): Result<Unit> {
        return jikCatching {
            val file = File.createTempFile("temp", null, context.cacheDir)
            context.contentResolver.openInputStream(contentUri).use { inputStream ->
                if (inputStream != null) {
                    FileOutputStream(file).use { fileOutputStream ->
                        fileOutputStream.write(inputStream.readBytes())
                        fileOutputStream.flush()
                    }
                } else {
                    throw Exception("inputStream is null")
                }
            }

            val requestBody = file.asRequestBody("video/mp4".toMediaTypeOrNull())
            val videoPart = MultipartBody.Part.createFormData("video", file.name, requestBody)

            videoService.upload(videoPart, description.toRequestBody())
        }
    }

    override suspend fun getVideos(): Result<List<Video>> {
        TODO("Not yet implemented")
    }
}