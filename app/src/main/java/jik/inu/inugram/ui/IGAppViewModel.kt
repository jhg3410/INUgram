package jik.inu.inugram.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jik.inu.data.repository.certification.CertificationRepository
import jik.inu.feature.certification.navigation.GreetingNavigation
import jik.inu.feature.home.navigation.HomeNavigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IGAppViewModel @Inject constructor(
    private val certificationRepository: CertificationRepository
) : ViewModel() {

    private var _startDestination = MutableStateFlow(GreetingNavigation.route)
    val startDestination = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            certificationRepository.getAccessToken().collectLatest {
                if (it.isEmpty()) {
                    _startDestination.value = GreetingNavigation.route
                } else {
                    _startDestination.value = HomeNavigation.route
                }
            }
        }
    }
}