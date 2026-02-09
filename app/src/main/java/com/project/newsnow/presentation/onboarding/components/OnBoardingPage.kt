package com.project.newsnow.presentation.onboarding.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.project.newsnow.presentation.Dimens.MediumPadding1
import com.project.newsnow.presentation.Dimens.MediumPadding2
import com.project.newsnow.presentation.onboarding.Page
import com.project.newsnow.presentation.onboarding.pages
import com.project.newsnow.ui.theme.NewsNowTheme

@Composable
fun OnBoardingPage(
    page: Page,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(bottom = MediumPadding1),
        verticalArrangement = Arrangement.spacedBy(MediumPadding2),
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            painter = painterResource(id = page.image),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            text = page.title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
            ),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            text = page.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )
    }


}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun OnBoardingPagePreviewNight() {
    NewsNowTheme() {
        OnBoardingPage(pages.first())
    }

}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
private fun OnBoardingPagePreviewDay() {
    NewsNowTheme() {
        OnBoardingPage(pages.first())
    }

}