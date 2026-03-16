package com.project.newsnow.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.project.newsnow.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    init {
        observeSearchQuery()
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            SearchEvent.SearchNews -> {
                searchNews()
            }

            is SearchEvent.UpdateSearchQuery -> {
                _state.update {
                    it.copy(searchQuery = event.query)
                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .debounce(500)
            .distinctUntilChanged()
            .onEach { query ->
                if (query.length >= 2) {
                    searchNews()
                }
            }
            .launchIn(viewModelScope)
    }


    private fun searchNews() {
        val articles = newsUseCases.searchNews(
            query = _state.value.searchQuery,
            sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
        ).cachedIn(viewModelScope)
        _state.update {
            it.copy(articles = articles)
        }
    }
}