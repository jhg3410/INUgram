package jik.inu.data.repository.certification

import kotlinx.coroutines.flow.Flow

interface CertificationRepository {

    suspend fun sendEmail(email: String): Result<String>

    suspend fun getAccessToken(email: String): Result<String>

    fun getAccessToken(): Flow<String>

    suspend fun saveAccessToken(accessToken: String)
}