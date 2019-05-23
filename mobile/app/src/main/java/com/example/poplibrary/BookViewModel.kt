package com.example.poplibrary

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application): AndroidViewModel(application) {
    private val repository: BookRepository
    private val allBooks: LiveData<List<Book>>

    init {
        val bookDAO = BookRoomDatabase.getDatabase(application, viewModelScope).bookDAO()
        repository = BookRepository(bookDAO)
        allBooks = repository.allBooks
    }

    fun insert(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(book)
    }
}