package com.project.newsnow.domain.repository

import androidx.paging.PagingData
import com.project.newsnow.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

}