package jik.inu.data.repository.certification

import jik.inu.data.datastore.CertificationPreferencesDataSource
import jik.inu.data.network.request.EmailRequest
import jik.inu.data.network.service.CertificationService
import jik.inu.data.util.jikCatching
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CertificationRepositoryImpl @Inject constructor(
    private val service: CertificationService,
    private val preferencesDataSource: CertificationPreferencesDataSource
) : CertificationRepository {
    override suspend fun sendEmail(email: String): Result<String> {
        return jikCatching {
            service.sendEmail(EmailRequest(email = email)).number
        }
    }

    override suspend fun getAccessToken(email: String): Result<String> {
        return jikCatching {
            service.getAccessToken(EmailRequest(email = email)).accessToken
        }
    }

    override fun getAccessToken(): Flow<String> {
        return preferencesDataSource.accessToken
    }

    override suspend fun saveAccessToken(accessToken: String) {
        preferencesDataSource.setAccessToken(accessToken)
    }
}