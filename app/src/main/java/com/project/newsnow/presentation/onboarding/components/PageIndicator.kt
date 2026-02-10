package com.project.newsnow.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.newsnow.presentation.Dimens.IndicatorSize
import com.project.newsnow.ui.theme.NewsNowTheme

@Composable
fun PageIndicator(
    pageQtt: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    val selectedColor = MaterialTheme.colorScheme.primary
    val unselectedColor = MaterialTheme.colorScheme.outline
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        repeat(pageQtt) { page ->
            Box(
                modifier = Modifier
                    .size(IndicatorSize)
                    .clip(CircleShape)
                    .background(if (page == currentPage) selectedColor else unselectedColor),
            )

        }
    }

}

@Preview
@Composable
private fun PageIndicatorPreview() {
    NewsNowTheme() {
        PageIndicator(
            3,
            1
        )
    }
}