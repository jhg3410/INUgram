package jik.inu.feature.certification.email

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jik.inu.data.repository.certification.CertificationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailViewModel @Inject constructor(
    private val certificationRepository: CertificationRepository
) : ViewModel() {

    var certificationNumber = MutableStateFlow("")
    var inputEmail = MutableStateFlow("")
    val isLoading = MutableStateFlow(false)

    fun sendEmail(email: String) {
        isLoading.value = true
        viewModelScope.launch {
            certificationRepository.sendEmail("$email@inu.ac.kr").onSuccess { number ->
                changeCertificationNumber(number = number)
            }.onFailure {
                // todo: error handling
            }.also {
                isLoading.value = false
            }
        }
    }

    fun changeEmail(email: String) {
        inputEmail.value = email
    }

    fun changeCertificationNumber(number: String) {
        certificationNumber.value = number
    }
}