package com.project.newsnow.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.project.newsnow.domain.model.Article
import com.project.newsnow.presentation.common.ArticlesList
import com.project.newsnow.presentation.common.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    onEvent: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    Scaffold(
        topBar = {
            SearchBar(
                value = state.searchQuery,
                onValueChange = {
                    onEvent(SearchEvent.UpdateSearchQuery(it))
                },
                readOnly = false,
                onSearch = {},
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    ) { paddingValues ->
        if (state.searchQuery.isEmpty()) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Search for news")
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                state.articles?.let {
                    val articles = it.collectAsLazyPagingItems()

                    ArticlesList(
                        articles = articles,
                        onClick = { article ->
                            navigateToDetails(article)
                        }
                    )
                }
            }
        }
    }
}