package com.project.newsnow.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.newsnow.domain.usecases.app_entry.AppEntryUseCases
import com.project.newsnow.presentation.onboarding.components.OnBoardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(OnBoardingState())
    val state = _state.asStateFlow()


    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            OnBoardingEvent.NextClicked -> nextPage()
            OnBoardingEvent.BackClicked -> previousPage()
            OnBoardingEvent.SaveAppEntry -> saveAppEntry()
        }
    }

    private fun previousPage() {
        val current = _state.value.currentPage
        if (current > 0) {
            _state.update {
                it.copy(currentPage = current - 1)
            }
        }
    }

    private fun nextPage() {
        val current = _state.value.currentPage
        if (_state.value.isLastPage) {
            saveAppEntry()
        } else {
            _state.update {
                it.copy(currentPage = current + 1)
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }
}