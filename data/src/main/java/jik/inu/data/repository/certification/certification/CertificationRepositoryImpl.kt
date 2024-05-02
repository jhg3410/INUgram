package jik.inu.data.repository.certification.certification

import jik.inu.data.network.request.EmailRequest
import jik.inu.data.network.service.CertificationService
import jik.inu.data.util.jikCatching
import javax.inject.Inject

class CertificationRepositoryImpl @Inject constructor(
    private val service: CertificationService
) : CertificationRepository {
    override suspend fun sendEmail(email: String): Result<String> {
        return jikCatching {
            service.sendEmail(EmailRequest(email = email)).number
        }
    }

    override suspend fun getAccessToken(): Result<Unit> {
        TODO("Not yet implemented")
    }
}