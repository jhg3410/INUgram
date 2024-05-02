package jik.inu.feature.home

import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jik.inu.core.designsystem.component.toast.ToastType
import jik.inu.data.repository.video.VideoRepository
import jik.inu.feature.home.navigation.UploadArgs
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

    val visibleToast = MutableStateFlow(false)
    val toastType = MutableStateFlow(ToastType.SUCCESS)
    val toastMessage = MutableStateFlow("")

    fun upload() {
        viewModelScope.launch {
            videoRepository.upload(
                contentUri = contentUri,
                description = inputDescription.value.text
            ).onSuccess {
                toastType.value = ToastType.SUCCESS
                toastMessage.value = "정상적으로 업로드 되었어요"
            }.onFailure {
                toastType.value = ToastType.ERROR
                toastMessage.value = it.message ?: "업로드에 실패했어요"
            }.also {
                visibleToast.value = true
            }
        }
    }

    fun closeToast() {
        visibleToast.value = false
    }

    fun changeDescription(textFieldValue: TextFieldValue) {
        inputDescription.value = textFieldValue
    }
}

