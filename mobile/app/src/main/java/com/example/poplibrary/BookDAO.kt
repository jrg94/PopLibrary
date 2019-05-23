package com.example.poplibrary

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDAO {

    @Query("SELECT * from book_table ORDER BY title ASC")
    fun getAllBooks(): MutableList<Book>

    @Insert
    suspend fun insert(book: Book)

    @Query("DELETE FROM book_table")
    fun deleteAll()
}