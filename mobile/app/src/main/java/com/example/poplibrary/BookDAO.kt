package com.example.poplibrary

import android.arch.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDAO {

    @Query("SELECT * from book_table ORDER BY title ASC")
    fun getAllBooks(): LiveData<List<Book>>

    @Insert
    suspend fun insert(book: Book)

    @Query("DELETE FROM book_table")
    fun deleteAll()
}