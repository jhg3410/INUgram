package jik.inu.feature.upload

import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jik.inu.data.repository.video.VideoRepository
import jik.inu.feature.upload.navigation.UploadArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val videoRepository: VideoRepository
) : ViewModel() {

    val contentUri = UploadArgs(savedStateHandle).contentUri.toUri()
    val inputDescription = MutableStateFlow(TextFieldValue())

    val isLoading = MutableStateFlow(false)

    fun upload(onSuccess: (message: String) -> Unit, onFailure: (message: String) -> Unit) {
        viewModelScope.launch {
            isLoading.value = true

            videoRepository.upload(
                contentUri = contentUri,
                description = inputDescription.value.text
            ).onSuccess {
                onSuccess("정상적으로 업로드 되었어요")
            }.onFailure {
                onFailure(it.message ?: "업로드에 실패했어요")
            }.also {
                isLoading.value = false
            }
        }
    }

    fun changeDescription(textFieldValue: TextFieldValue) {
        inputDescription.value = textFieldValue
    }
}

