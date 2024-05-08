package jik.inu.data.network.service

import jik.inu.data.network.response.GetVideosResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface VideoService {

    @Multipart
    @POST("/api/v1/upload/video")
    suspend fun upload(
        @Part file: MultipartBody.Part,
        @Part("title") title: RequestBody
    )


    @GET("/api/v1/find/video")
    suspend fun getVideos(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): GetVideosResponse
}