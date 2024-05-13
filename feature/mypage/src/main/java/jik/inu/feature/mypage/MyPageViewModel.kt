package jik.inu.feature.mypage

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jik.inu.core.model.Video
import jik.inu.data.repository.user.UserRepository
import jik.inu.data.repository.video.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val videoRepository: VideoRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val tabs = listOf("좋아요한 영상", "내가 올린 영상")
    var selectedTabIndex = MutableStateFlow(0)
    val email = MutableStateFlow("")
    val profileColor = MutableStateFlow(Color.Transparent)

    val likedVideos = MutableStateFlow(emptyList<Video>())
    val myVideos = MutableStateFlow(emptyList<Video>())

    init {
        getMyInfo()
        getLikedVideos()
        getMyVideos()
    }

    fun onSelectedTabChanged(index: Int) {
        selectedTabIndex.value = index
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

    private fun getLikedVideos() {
        viewModelScope.launch {
            videoRepository.getLikedVideos()
                .onSuccess {
                    likedVideos.value = it
                }.onFailure {
                    // TODO: Handle error
                }
        }
    }

    private fun getMyVideos() {
        viewModelScope.launch {
            videoRepository.getMyVideos()
                .onSuccess {
                    myVideos.value = it
                }.onFailure {
                    // TODO: Handle error
                }
        }
    }

    fun handleLike(video: Video) {
        if (likedVideos.value.contains(video)) {
            dislikeVideo(video)
        } else {
            likeVideo(video)
        }
    }

    private fun likeVideo(video: Video) {
        likedVideos.value += video
        viewModelScope.launch {
            videoRepository.like(videoId = video.id)
        }
    }

    private fun dislikeVideo(video: Video) {
        likedVideos.value -= video
        viewModelScope.launch {
            videoRepository.disLike(videoId = video.id)
        }
    }
}