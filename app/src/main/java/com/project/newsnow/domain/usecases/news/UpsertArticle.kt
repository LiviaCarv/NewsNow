package com.project.newsnow.domain.usecases.news

import com.project.newsnow.data.local.ArticleDao
import com.project.newsnow.domain.model.Article

class UpsertArticle(
    private val newsDao: ArticleDao
) {
    suspend operator fun invoke(article: Article) {
        return newsDao.upsert(article)

    }
}