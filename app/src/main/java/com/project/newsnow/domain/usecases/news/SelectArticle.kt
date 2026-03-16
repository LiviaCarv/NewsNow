package com.project.newsnow.domain.usecases.news

import com.project.newsnow.domain.model.Article
import com.project.newsnow.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): Article? {
        return newsRepository.getArticleByUrl(url)

    }
}