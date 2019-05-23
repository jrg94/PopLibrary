package com.example.poplibrary

import androidx.lifecycle.LiveData
import androidx.annotation.WorkerThread

class BookRepository(private val bookDAO: BookDAO) {
    val allBooks: LiveData<List<Book>> = bookDAO.getAllBooks()

    @WorkerThread
    suspend fun insert(book: Book) {
        bookDAO.insert(book)
    }
}