package com.therenegadecoder.poplibrary

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class BookRepository(private val bookDAO: BookDAO) {
    val allBooks: LiveData<List<Book>> = bookDAO.getAllBooks()

    @WorkerThread
    suspend fun insert(book: Book) {
        bookDAO.insert(book)
    }
}