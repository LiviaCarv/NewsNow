package com.project.newsnow.domain.usecases.news

import com.project.newsnow.data.local.ArticleDao
import com.project.newsnow.domain.model.Article

class SelectArticle(
    private val newsDao: ArticleDao
) {
    suspend operator fun invoke(url: String): Article? {
        return newsDao.getArticleByUrl(url)

    }
}