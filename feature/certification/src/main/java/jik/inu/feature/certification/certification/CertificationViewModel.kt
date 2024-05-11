package jik.inu.feature.certification.certification

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jik.inu.data.repository.certification.CertificationRepository
import jik.inu.feature.certification.navigation.CertificationArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CertificationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val certificationRepository: CertificationRepository
) : ViewModel() {

    private val email = CertificationArgs(savedStateHandle).email
    private val certificationNumber = CertificationArgs(savedStateHandle).certificationNumber
    val inputNumber = MutableStateFlow("")
    var visibleToast = MutableStateFlow(false)

    fun certify(action: () -> Unit) {
        if (inputNumber.value != certificationNumber) {
            visibleToast.value = true
            return
        } else {
            viewModelScope.launch {
                launch {
                    certificationRepository.getAccessToken(email = "$email@inu.ac.kr").run {
                        onSuccess {
                            certificationRepository.saveAccessToken(it)
                        }.onFailure {
                            // TODO: 에러 처리
                        }
                    }
                }.join()
                action()
            }
        }
    }

    fun changeInputNumber(input: String) {
        with(input) {
            if (isEmpty() || toIntOrNull() != null && length <= 5) {
                inputNumber.value = this
            }
        }
    }

    fun closeToast() {
        visibleToast.value = false
    }
}