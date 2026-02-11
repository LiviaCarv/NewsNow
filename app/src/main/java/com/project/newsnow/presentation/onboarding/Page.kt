package com.project.newsnow.presentation.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Page(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int
)