package com.project.newsnow.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.project.newsnow.data.remote.api.NewsAPI
import com.project.newsnow.domain.model.Article

class NewsPagingSource(
    private val newsApi: NewsAPI,
    private val sources: String
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.let { page ->
                page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
            }
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val response =
                newsApi.getNews(sources = sources, page = page, pageSize = params.loadSize)
            val articles = response.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (articles.isEmpty()) null else page + 1,
                prevKey = if (page == 1) null else page - 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(throwable = exception)
        }

    }
}