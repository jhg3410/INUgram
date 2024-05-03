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

    init {
        getVideos()
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
}
