package jik.inu.feature.mypage

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
) : ViewModel() {

    val tabs = listOf("좋아요한 영상", "내가 올린 영상")
    var selectedTabIndex = MutableStateFlow(0)

    fun onSelectedTabChanged(index: Int) {
        selectedTabIndex.value = index
    }
}