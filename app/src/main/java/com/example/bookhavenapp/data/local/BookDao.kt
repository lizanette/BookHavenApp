package com.example.bookhavenapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookhavenapp.domain.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: Item): Long

    @Delete
    suspend fun delete(item: Item): Int

    @Query("SELECT * FROM Item")
    fun getBooks(): Flow<List<Item>>

    @Query("SELECT * FROM Item WHERE id = :id")
    suspend fun getBook(id: String): Item?
}
