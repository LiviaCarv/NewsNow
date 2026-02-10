package com.project.newsnow.di

import android.app.Application
import com.project.newsnow.data.manager.LocalUserManagerImpl
import com.project.newsnow.domain.manager.LocalUserManager
import com.project.newsnow.domain.usecases.app_entry.AppEntryUseCases
import com.project.newsnow.domain.usecases.app_entry.ReadAppEntry
import com.project.newsnow.domain.usecases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

}