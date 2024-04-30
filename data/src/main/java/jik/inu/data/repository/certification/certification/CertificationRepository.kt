package jik.inu.data.repository.certification.certification

interface CertificationRepository {

    suspend fun sendEmail(email: String): Result<String>

    suspend fun getAccessToken(): Result<Unit>
}