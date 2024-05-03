package jik.inu.data.repository.video

import android.net.Uri
import jik.inu.core.model.Video
import javax.inject.Inject

class VideoRepositoryMock @Inject constructor(
) : VideoRepository {
    override suspend fun upload(contentUri: Uri, description: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getVideos(): Result<List<Video>> {
        return Result.success(
            listOf(
                Video(
                    url = "https://studyhub-bucket.s3.ap-northeast-2.amazonaws.com/temp606994729085618417.tmp",
                    description = "하이"
                ),
                Video(
                    url = "https://studyhub-bucket.s3.ap-northeast-2.amazonaws.com/temp8415934949538586251.tmp",
                    description = "귀여운 강아지"
                ),
                Video(
                    url = "https://studyhub-bucket.s3.ap-northeast-2.amazonaws.com/300%EC%9B%90%EC%9D%84%20%EC%9D%B8%EC%83%9D%EC%97%90%EC%84%9C%20%EA%B0%80%EC%9E%A5%20%EB%A7%9B%EC%9E%88%EA%B2%8C%20%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%20%EB%B0%A9%EB%B2%95%20%EC%87%BC%EC%B8%A0%20%EB%B2%84%EC%A0%84%EC%9E%85%EB%8B%88%EB%8B%A4.%20%23Shorts%20%28UOdqPpa7IiI%29.mp4",
                    description = "마싯겠당"
                ),
            )
        )
    }
}