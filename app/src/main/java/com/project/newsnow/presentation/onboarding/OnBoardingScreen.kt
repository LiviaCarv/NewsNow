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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.newsnow.presentation.Dimens.MediumPadding2
import com.project.newsnow.presentation.Dimens.PageIndicatorWidth
import com.project.newsnow.presentation.common.NewsButton
import com.project.newsnow.presentation.onboarding.components.OnBoardingPage
import com.project.newsnow.presentation.onboarding.components.PageIndicator
import com.project.newsnow.ui.theme.NewsNowTheme
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }
        HorizontalPager(state = pagerState) { page ->
            OnBoardingPage(page = pages[page])
        }
        Row(
            modifier = modifier
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
                pageQtt = pages.size,
                currentPage = pagerState.currentPage
            )
            Row() {
                val scope = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    NewsButton(buttonState.value[0], false) {
                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                        }
                    }
                }
                NewsButton(buttonState.value[1]) {
                    scope.launch {
                        if (pagerState.currentPage == 2) {
                            //TODO: Navigate to Home Screen
                        } else {
                            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    }
                }


            }
        }
    }

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun OnBoardingScreenPreviewNight() {
    NewsNowTheme() {
        OnBoardingScreen()
    }
}