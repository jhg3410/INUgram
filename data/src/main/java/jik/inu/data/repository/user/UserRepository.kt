package jik.inu.data.repository.user

import jik.inu.core.model.User

interface UserRepository {

    suspend fun getMyInfo(): Result<User>
}