package com.project.newsnow.domain.usecases.news

import com.project.newsnow.data.local.ArticleDao
import com.project.newsnow.domain.model.Article

class DeleteArticle(
    private val newsDao: ArticleDao
) {
    suspend operator fun invoke(article: Article) {
        return newsDao.delete(article)

    }
}