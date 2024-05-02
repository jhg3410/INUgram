package jik.inu.data.repository.certification

interface CertificationRepository {

    suspend fun sendEmail(email: String): Result<String>

    suspend fun getAccessToken(): Result<Unit>
}