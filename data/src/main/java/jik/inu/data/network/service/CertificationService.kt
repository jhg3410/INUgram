package jik.inu.data.network.service

import jik.inu.data.network.request.EmailRequest
import jik.inu.data.network.response.CertificationNumber
import retrofit2.http.Body
import retrofit2.http.POST

interface CertificationService {

    @POST("/api/v1/email")
    suspend fun sendEmail(
        @Body email: EmailRequest
    ): CertificationNumber

    @POST("/api/v1/certification")
    suspend fun checkCertificationNumber(
        @Body certificationNumber: String
    ): Boolean
}