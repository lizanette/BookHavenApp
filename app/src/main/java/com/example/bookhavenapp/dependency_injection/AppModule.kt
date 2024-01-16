package com.example.bookhavenapp.dependency_injection

import android.app.Application
import androidx.room.Room
import com.example.bookhavenapp.data.local.BookDao
import com.example.bookhavenapp.data.local.BookDatabase
import com.example.bookhavenapp.data.local.BookTypeConverter
import com.example.bookhavenapp.data.manager.LocalUserManagerImplementation
import com.example.bookhavenapp.data.remote.BooksApi
import com.example.bookhavenapp.data.repository.BooksRepositoryImplementation
import com.example.bookhavenapp.domain.manager.LocalUserManager
import com.example.bookhavenapp.domain.repository.BooksRepository
import com.example.bookhavenapp.domain.use_cases.app_entry.AppEntryUseCases
import com.example.bookhavenapp.domain.use_cases.app_entry.ReadAppEntry
import com.example.bookhavenapp.domain.use_cases.app_entry.SaveAppEntry
import com.example.bookhavenapp.domain.use_cases.books.BooksUseCases
import com.example.bookhavenapp.domain.use_cases.books.DeleteBook
import com.example.bookhavenapp.domain.use_cases.books.GetBooks
import com.example.bookhavenapp.domain.use_cases.books.SearchBooks
import com.example.bookhavenapp.domain.use_cases.books.SelectBook
import com.example.bookhavenapp.domain.use_cases.books.SelectBooks
import com.example.bookhavenapp.domain.use_cases.books.UpsertBook
import com.example.bookhavenapp.utils.Constants.BASE_URL
import com.example.bookhavenapp.utils.Constants.BOOKS_DATABASE_NAME
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

    @Provides
    @Singleton
    fun provideBooksApi(): BooksApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(BooksApi::class.java)

    }

    @Provides
    @Singleton
    fun provideBooksRepository(
        booksApi: BooksApi,
        bookDao: BookDao
    ): BooksRepository = BooksRepositoryImplementation(booksApi, bookDao)

    @Provides
    @Singleton
    fun provideBooksUseCases(
        booksRepository: BooksRepository
    ): BooksUseCases {
        return BooksUseCases(
            getBooks = GetBooks(booksRepository),
            searchBooks = SearchBooks(booksRepository),
            selectBook = SelectBook(booksRepository),
            upsertBook = UpsertBook(booksRepository),
            deleteBook = DeleteBook(booksRepository),
            selectBooks = SelectBooks(booksRepository),
        )
    }

    @Provides
    @Singleton
    fun provideBooksDatabase(
        application: Application
    ): BookDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = BookDatabase::class.java,
            name = BOOKS_DATABASE_NAME
        ).addTypeConverter(BookTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideBookDao(
        booksDatabase: BookDatabase
    ): BookDao = booksDatabase.bookDao
}
