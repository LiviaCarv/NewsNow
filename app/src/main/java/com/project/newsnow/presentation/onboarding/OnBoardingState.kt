package com.project.newsnow.presentation.onboarding

import androidx.annotation.StringRes
import com.project.newsnow.R

data class OnBoardingState(
    val pages: List<Page> = pagesList,
    val currentPage: Int = 0,
) {
    val isFirstPage: Boolean = currentPage == 0
    val isLastPage: Boolean = currentPage == pages.lastIndex

    val backButtonVisible: Boolean = !isFirstPage

    @get:StringRes
    val nextButtonTextRes: Int =
        if (isLastPage) R.string.onboarding_get_started else R.string.onboarding_next
}

val pagesList = listOf(
    Page(
        title = R.string.onboarding_page_1_title,
        description = R.string.onboarding_page_1_description,
        image = R.drawable.onboarding1
    ),
    Page(
        title = R.string.onboarding_page_2_title,
        description = R.string.onboarding_page_2_description,
        image = R.drawable.onboarding2
    ),
    Page(
        title = R.string.onboarding_page_3_title,
        description = R.string.onboarding_page_3_description,
        image = R.drawable.onboarding3
    )
)