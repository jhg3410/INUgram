package jik.inu.data.network.service

import jik.inu.core.model.User
import retrofit2.http.GET

interface UserService {

    @GET("/api/v1/find/user")
    suspend fun getMyInfo(): User
}