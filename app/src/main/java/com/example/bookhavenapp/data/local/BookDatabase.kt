package com.example.bookhavenapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookhavenapp.domain.model.Item

@Database(entities = [Item::class], version = 1)
@TypeConverters(BookTypeConverter::class)
abstract class BookDatabase: RoomDatabase() {
    abstract val bookDao: BookDao
}
