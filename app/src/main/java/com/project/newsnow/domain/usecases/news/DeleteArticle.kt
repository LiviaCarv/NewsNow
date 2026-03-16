package com.project.newsnow.domain.usecases.news

import com.project.newsnow.data.local.ArticleDao
import com.project.newsnow.domain.model.Article
import com.project.newsnow.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article) {
        return newsRepository.deleteArticle(article)

    }
}