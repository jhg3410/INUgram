package jik.inu.data.repository.user

import jik.inu.core.model.User
import jik.inu.data.network.service.UserService
import jik.inu.data.util.jikCatching
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {
    override suspend fun getMyInfo(): Result<User> {
        return jikCatching {
            userService.getMyInfo()
        }
    }
}