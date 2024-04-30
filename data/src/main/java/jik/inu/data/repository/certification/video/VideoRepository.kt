package jik.inu.data.repository.certification.video

interface VideoRepository {

    suspend fun upload(): Result<Unit>
}