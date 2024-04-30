package jik.inu.data.repository.certification.video

import android.util.Log
import javax.inject.Inject

class VideoRepositoryMock @Inject constructor() : VideoRepository {
    override suspend fun upload(): Result<Unit> {
        return runCatching {
//            throw Exception("Not yet implemented")
            Log.d("VideoRepositoryMock", "upload")
        }
    }
}