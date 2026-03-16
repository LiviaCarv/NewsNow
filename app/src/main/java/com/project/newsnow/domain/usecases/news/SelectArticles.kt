package com.project.newsnow.domain.usecases.news

import com.project.newsnow.domain.model.Article
import com.project.newsnow.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()

    }
}