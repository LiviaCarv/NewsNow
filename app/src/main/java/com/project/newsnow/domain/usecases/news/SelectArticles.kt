package com.project.newsnow.domain.usecases.news

import com.project.newsnow.data.local.ArticleDao
import com.project.newsnow.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: ArticleDao
) {
    operator fun invoke() : Flow<List<Article>> {
        return newsDao.getArticles()

    }
}