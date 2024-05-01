package jik.inu.data.network.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface VideoService {

    @Multipart
    @POST("api/v1/upload/video")
    suspend fun upload(
        @Part file: MultipartBody.Part,
        @Part("title") title: RequestBody = "hihi".toRequestBody()
    )
}