package com.therenegadecoder.poplibrary.data

import org.junit.Assert.*

class BookTest {

    @org.junit.Test
    fun matchTitle() {
        val book = Book(title = "Hello, World!")
        assertTrue(book.match("Hello"))
    }
}