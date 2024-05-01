package jik.inu.data.repository.certification.video

import android.net.Uri

interface VideoRepository {

    suspend fun upload(contentUri: Uri, description: String): Result<Unit>
}