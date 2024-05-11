package jik.inu.feature.upload

import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jik.inu.core.designsystem.component.toast.ToastType
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

    val visibleToast = MutableStateFlow(false)
    val toastType = MutableStateFlow(ToastType.SUCCESS)
    val toastMessage = MutableStateFlow("")

    val isLoading = MutableStateFlow(false)

    fun upload(onSuccess: () -> Unit) {
        viewModelScope.launch {
            isLoading.value = true

            videoRepository.upload(
                contentUri = contentUri,
                description = inputDescription.value.text
            ).onSuccess {
                toastType.value = ToastType.SUCCESS
                toastMessage.value = "정상적으로 업로드 되었어요"
                onSuccess()
            }.onFailure {
                toastType.value = ToastType.ERROR
                toastMessage.value = it.message ?: "업로드에 실패했어요"
            }.also {
                isLoading.value = false
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

