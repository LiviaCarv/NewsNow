package com.project.newsnow.presentation.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.project.newsnow.R
import com.project.newsnow.presentation.Dimens.MediumPadding2
import com.project.newsnow.presentation.Dimens.PageIndicatorWidth
import com.project.newsnow.presentation.common.NewsButton
import com.project.newsnow.presentation.onboarding.components.OnBoardingPage
import com.project.newsnow.presentation.onboarding.components.PageIndicator
import com.project.newsnow.ui.theme.NewsNowTheme
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun OnBoardingScreen(
    state: OnBoardingState,
    onEvent: (OnBoardingEvent) -> Unit
) {
    val pagerState = rememberPagerState(
        pageCount = { state.pages.size }
    )

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .distinctUntilChanged()
            .collect { page ->
                onEvent(OnBoardingEvent.PageChanged(page))
            }
    }

    LaunchedEffect(state.currentPage) {
        if (!pagerState.isScrollInProgress) {
            pagerState.animateScrollToPage(state.currentPage)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        HorizontalPager(state = pagerState) { page ->
            OnBoardingPage(page = state.pages[page])
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PageIndicator(
                modifier = Modifier
                    .width(PageIndicatorWidth)
                    .weight(1f),
                pageQtt = state.pages.size,
                currentPage = state.currentPage
            )
            Row {
                if (state.backButtonVisible) {
                    NewsButton(
                        stringResource(id = R.string.onboarding_back),
                        false,
                        onClick = {
                            onEvent(OnBoardingEvent.BackClicked)
                        })
                }
                NewsButton(stringResource(id = state.nextButtonTextRes)) {
                    onEvent(OnBoardingEvent.NextClicked)
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun OnBoardingScreenPreviewNight() {
    NewsNowTheme() {
        OnBoardingScreen(state = OnBoardingState(), {})
    }
}