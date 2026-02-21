package com.project.newsnow.data.remote.dto

import com.project.newsnow.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)