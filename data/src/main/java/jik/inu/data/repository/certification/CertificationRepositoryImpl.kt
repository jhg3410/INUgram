package jik.inu.data.repository.certification

import jik.inu.data.network.request.EmailRequest
import jik.inu.data.network.service.CertificationService
import javax.inject.Inject

class CertificationRepositoryImpl @Inject constructor(
    private val service: CertificationService
) : CertificationRepository {
    override suspend fun sendEmail(email: String): Result<String> {
        return runCatching {
            service.sendEmail(EmailRequest(email = email)).number
        }
    }

    override suspend fun getAccessToken(): Result<Unit> {
        TODO("Not yet implemented")
    }
}