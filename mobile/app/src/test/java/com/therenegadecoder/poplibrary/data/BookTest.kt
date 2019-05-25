package com.therenegadecoder.poplibrary.data

import org.junit.Assert.*

class BookTest {

    @org.junit.Test
    fun matchTitle() {
        val book = Book(title = "Hello, World!")
        assertTrue("Failed to match ${book.title} to Hello", book.match("Hello"))
    }

    @org.junit.Test
    fun matchAuthor() {
        val book = Book(author = "James Earl Jones")
        assertTrue("Failed to match ${book.author} to Earl", book.match("Earl"))
    }

    @org.junit.Test
    fun matchEditor() {
        val book = Book(editor = "The Pittsburgh Penguins")
        assertTrue(book.match("Penguin"))
    }

    @org.junit.Test
    fun matchLanguage() {
        val book = Book(language = "English")
        assertTrue(book.match("English"))
    }

    @org.junit.Test
    fun matchISBN13() {
        val book = Book(isbn13 = "976-143136645")
        assertFalse(book.match("66"))
    }

    @org.junit.Test
    fun matchNone() {
        val book = Book(title = "The Great Gatsby", language = "English", author = "Stephen Colbert")
        assertFalse(book.match("Art"))
    }

}