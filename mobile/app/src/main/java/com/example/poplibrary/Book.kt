package com.example.poplibrary

import java.util.*

class Book(
    val isbn13: String? = null,
    val title: String? = null,
    val author: String? = null,
    val editor: String? = null,
    val language: String? = null,
    val coverImageURL: String? = null,
    val pageCount: Int? = null,
    val dateOfPublication: Date? = null
) {

    /**
     * Converts the book to a string.
     */
    override fun toString(): String {
        return title!!
    }

    fun match(str: CharSequence): Boolean {
        return (title?.contains(str, true)) == true
    }
}
