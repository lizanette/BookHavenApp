package com.example.bookhavenapp.dependency_injection

import android.app.Application
import com.example.bookhavenapp.data.manager.LocalUserManagerImplementation
import com.example.bookhavenapp.domain.manager.LocalUserManager
import com.example.bookhavenapp.domain.use_cases.app_entry.AppEntryUseCases
import com.example.bookhavenapp.domain.use_cases.app_entry.ReadAppEntry
import com.example.bookhavenapp.domain.use_cases.app_entry.SaveAppEntry
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
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImplementation(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}
