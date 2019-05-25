package com.therenegadecoder.poplibrary.backend

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.therenegadecoder.poplibrary.data.Book

@Dao
interface BookDAO {

    @Query("SELECT * from book_table ORDER BY title ASC")
    fun getAllBooks(): LiveData<List<Book>>

    @Insert
    suspend fun insert(book: Book)

    @Query("DELETE FROM book_table")
    suspend fun deleteAll()
}