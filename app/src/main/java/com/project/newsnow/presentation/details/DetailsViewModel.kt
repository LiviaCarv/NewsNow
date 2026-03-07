package com.project.newsnow.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.newsnow.domain.model.Article
import com.project.newsnow.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    var state by mutableStateOf(DetailsState())
        private set

    fun setArticle(article: Article) {
        state = state.copy(article = article)
    }

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.selectArticle(event.article.url)
                    if (article == null) {
                        upsertArticle(article = event.article)
                    } else {
                        deleteArticle(article = event.article)
                    }
                }
            }

            DetailsEvent.RemoveSideEffect -> {
                state = state.copy(sideEffect = null)
            }
        }
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticle(article)
        state = state.copy(sideEffect = "Article saved.")
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article)
        state = state.copy(sideEffect = "Article deleted.")
    }
}