package jik.inu.data.repository.certification.video

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import jik.inu.data.network.service.VideoService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val videoService: VideoService
) : VideoRepository {

    override suspend fun upload(
        contentUri: Uri
    ): Result<Unit> {
        return runCatching {
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
            videoService.upload(videoPart)
        }
    }
}