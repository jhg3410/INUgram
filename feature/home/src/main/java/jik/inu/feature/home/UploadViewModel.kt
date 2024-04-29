package jik.inu.feature.home

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jik.inu.feature.home.navigation.UploadArgs
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val contentUri = UploadArgs(savedStateHandle).contentUri
    val inputDescription = MutableStateFlow(TextFieldValue())

    fun upload() {
        // upload logic
    }

    fun changeDescription(textFieldValue: TextFieldValue) {
        inputDescription.value = textFieldValue
    }
}

