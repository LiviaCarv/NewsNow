package com.project.newsnow.presentation.search

import androidx.paging.PagingData
import com.project.newsnow.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)
