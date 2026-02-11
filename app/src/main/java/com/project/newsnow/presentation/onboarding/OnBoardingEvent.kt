package com.project.newsnow.presentation.onboarding

sealed class OnBoardingEvent {

    data object NextClicked : OnBoardingEvent()
    data object BackClicked : OnBoardingEvent()
    data object SaveAppEntry : OnBoardingEvent()
}