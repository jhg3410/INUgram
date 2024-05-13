package jik.inu.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jik.inu.core.model.Video
import jik.inu.data.repository.video.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val videoRepository: VideoRepository
) : ViewModel() {

    val videos = MutableStateFlow(emptyList<Video>())
    val likedVideos = MutableStateFlow(emptyList<Int>())

    init {
        getVideos()
        getLikedVideos()
    }

    private fun getVideos() {
        viewModelScope.launch {
            videoRepository.getVideos()
                .onSuccess {
                    videos.value = it
                }.onFailure {
                    // todo: handle error
                }
        }
    }

    private fun getLikedVideos() {
        viewModelScope.launch {
            videoRepository.getLikedVideos()
                .onSuccess {
                    likedVideos.value = it.map { video -> video.id }
                }.onFailure {
                    // todo: handle error
                }
        }
    }

    fun handleLike(id: Int) {
        if (likedVideos.value.contains(id)) {
            dislikeVideo(id)
        } else {
            likeVideo(id)
        }
    }

    private fun likeVideo(id: Int) {
        likedVideos.value += id
        viewModelScope.launch {
            videoRepository.like(videoId = id)
        }
    }

    private fun dislikeVideo(id: Int) {
        likedVideos.value -= id
        viewModelScope.launch {
            videoRepository.disLike(videoId = id)
        }
    }
}
