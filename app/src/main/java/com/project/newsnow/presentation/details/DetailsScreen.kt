package com.project.newsnow.presentation.details

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.project.newsnow.domain.model.Article
import com.project.newsnow.domain.model.Source
import com.project.newsnow.presentation.details.components.DetailsTopBar
import com.project.newsnow.ui.theme.NewsNowTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    state: DetailsState,
    onEvent: (DetailsEvent) -> Unit = {},
    navigateUp: () -> Unit = {}
) {
    val context = LocalContext.current
    val article = state.article ?: return

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            DetailsTopBar(
                onBackClick = navigateUp,
                onBookmarkClick = {
                    onEvent(DetailsEvent.UpsertDeleteArticle(article))
                },
                onShareClick = {
                    Intent(Intent.ACTION_SEND).also {
                        it.putExtra(Intent.EXTRA_TEXT, article.url)
                        it.type = "text/plain"
                        if (it.resolveActivity(context.packageManager) != null) {
                            context.startActivity(it)
                        }
                    }
                },
                onOpenInBrowserClick = {
                    Intent(Intent.ACTION_VIEW).also {
                        it.data = article.url.toUri()
                        if (it.resolveActivity(context.packageManager) != null) {
                            context.startActivity(it)
                        }
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            AsyncImage(
                model = article.urlToImage,
                contentDescription = article.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = article.description,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                lineHeight = 24.sp
            )

            Text(
                text = article.content,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DetailsScreenPreview() {
    NewsNowTheme {
        DetailsScreen(
            state = DetailsState(
                article = Article(
                    "Russell Brandon",
                    "Chip giant and world’s most valuable company Nvidia reported record profits in its most recent quarter on Wednesday, as demand for AI compute continues to skyrocket.\r\n“The demand for tokens in the wo… [+2287 chars]",
                    "The demand for tokens in the world has gone completely exponential,\" Nvidia CEO Jensen Huang said about the company's earnings.",
                    "02-02-26 16:30",
                    Source("Test Source", "BBC NEWS"),
                    "Nvidia has another record quarter amid record capex spends | TechCrunch",
                    "https://techcrunch.com/2026/02/25/nvidia-earnings-record-capex-spend-ai/",
                    " \"https://techcrunch.com/wp-content/uploads/2025/07/GettyImages-2219673294.jpg?resize=1200,750"
                )
            )
        )
    }

}