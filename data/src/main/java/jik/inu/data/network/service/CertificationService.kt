package jik.inu.data.network.service

import jik.inu.data.network.request.EmailRequest
import jik.inu.data.network.response.AccessTokenResponse
import jik.inu.data.network.response.CertificationNumberResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CertificationService {

    @POST("/api/v1/email")
    suspend fun sendEmail(
        @Body email: EmailRequest
    ): CertificationNumberResponse

    @GET("/api/v1/token/issue")
    suspend fun getAccessToken(
        @Body email: EmailRequest
    ): AccessTokenResponse
}