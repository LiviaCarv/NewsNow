package com.project.newsnow.di

import android.app.Application
import androidx.room.Room
import com.project.newsnow.data.local.ArticleDao
import com.project.newsnow.data.local.ArticleTypeConverter
import com.project.newsnow.data.local.NewsDatabase
import com.project.newsnow.data.manager.LocalUserManagerImpl
import com.project.newsnow.data.remote.api.NewsAPI
import com.project.newsnow.data.repository.NewsRepositoryImpl
import com.project.newsnow.domain.manager.LocalUserManager
import com.project.newsnow.domain.repository.NewsRepository
import com.project.newsnow.domain.usecases.app_entry.AppEntryUseCases
import com.project.newsnow.domain.usecases.app_entry.ReadAppEntry
import com.project.newsnow.domain.usecases.app_entry.SaveAppEntry
import com.project.newsnow.domain.usecases.news.DeleteArticle
import com.project.newsnow.domain.usecases.news.GetNews
import com.project.newsnow.domain.usecases.news.NewsUseCases
import com.project.newsnow.domain.usecases.news.SearchNews
import com.project.newsnow.domain.usecases.news.SelectArticles
import com.project.newsnow.domain.usecases.news.UpsertArticle
import com.project.newsnow.util.Constants.BASE_URL
import com.project.newsnow.util.Constants.NEWS_DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager = localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager = localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsAPI(): NewsAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsAPI: NewsAPI): NewsRepository =
        NewsRepositoryImpl(newsApi = newsAPI)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: ArticleDao
        ) = NewsUseCases(
        getNews = GetNews(newsRepository = newsRepository),
        searchNews = SearchNews(newsRepository = newsRepository),
        upsertArticle = UpsertArticle(newsDao = newsDao),
        deleteArticle = DeleteArticle(newsDao = newsDao),
        selectArticles = SelectArticles(newsDao = newsDao)
    )

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DB_NAME
        ).addTypeConverter(ArticleTypeConverter())
            .fallbackToDestructiveMigrationFrom()
            .build()

    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ) = newsDatabase.articleDao()

}