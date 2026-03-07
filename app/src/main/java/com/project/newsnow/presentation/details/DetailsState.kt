package com.project.newsnow.presentation.details

import com.project.newsnow.domain.model.Article

data class DetailsState(
    val article: Article? = null,
    val sideEffect: String? = null
)