package jik.inu.feature.mypage

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jik.inu.data.repository.user.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val tabs = listOf("좋아요한 영상", "내가 올린 영상")
    var selectedTabIndex = MutableStateFlow(0)
    val email = MutableStateFlow("")
    val profileColor = MutableStateFlow(Color.Transparent)

    init {
        getMyInfo()
    }

    private fun getMyInfo() {
        viewModelScope.launch {
            userRepository.getMyInfo()
                .onSuccess {
                    email.value = it.email
                    profileColor.value = Color(it.profileColor.removePrefix("0x").toLong(16))
                }.onFailure {
                    // TODO: Handle error
                }
        }
    }

    fun onSelectedTabChanged(index: Int) {
        selectedTabIndex.value = index
    }
}