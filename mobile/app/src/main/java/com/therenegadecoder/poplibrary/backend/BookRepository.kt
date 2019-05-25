package com.therenegadecoder.poplibrary.backend

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.therenegadecoder.poplibrary.data.Book

class BookRepository(private val bookDAO: BookDAO) {
    val allBooks: LiveData<List<Book>> = bookDAO.getAllBooks()

    @WorkerThread
    suspend fun insert(book: Book) {
        bookDAO.insert(book)
    }
}