package jik.inu.feature.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jik.inu.feature.home.navigation.UploadArgs
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val contentUri = UploadArgs(savedStateHandle).contentUri
}

