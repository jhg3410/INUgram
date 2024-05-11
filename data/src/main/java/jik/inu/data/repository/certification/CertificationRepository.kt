package jik.inu.data.repository.certification

interface CertificationRepository {

    suspend fun sendEmail(email: String): Result<String>

    suspend fun getAccessToken(email: String): Result<String>

    suspend fun saveAccessToken(accessToken: String)
}