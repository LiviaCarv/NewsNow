package com.project.newsnow.domain.usecases.news

import androidx.paging.PagingData
import com.project.newsnow.domain.model.Article
import com.project.newsnow.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(query: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(query = query, sources = sources)

    }
}