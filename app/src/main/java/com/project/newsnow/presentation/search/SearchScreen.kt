package com.project.newsnow.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
                onSearch = {
                    onEvent(SearchEvent.SearchNews)
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    ) { paddingValues ->
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