package com.project.newsnow.presentation.bookmark

import com.project.newsnow.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
) {
}