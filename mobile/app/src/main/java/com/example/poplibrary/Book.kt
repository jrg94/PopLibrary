package com.example.poplibrary

import android.graphics.Bitmap
import java.lang.AssertionError
import java.util.*

/**
 * Models a book.
 *
 * @author Jeremy Grifski
 */
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

    private var _coverImage: Bitmap? = null
    val coverImage: Bitmap
        get() {
            if (_coverImage == null) {
                _coverImage = ImageLoader().execute(this.coverImageURL).get()
            }
            return _coverImage ?: throw AssertionError("Set to null by another thread")
        }

    /**
     * Converts the book to a string.
     *
     * @return book as a string
     */
    override fun toString(): String {
        return title!!
    }

    /**
     * Tests whether or not the given character sequence matches to this book.
     *
     * @param str some character sequence for query
     * @return true if the character sequence matches any part of the book
     */
    fun match(str: CharSequence): Boolean {
        return checkContains(title, str)
                || checkContains(author, str)
                || checkContains(editor, str)
                || checkContains(language, str)
    }

    /**
     * A helper method for checking whether or not the field contains some character sequence.
     * If the field is null, the expression returns false.
     *
     * @param field a character sequence field of this object to compare against toFind
     * @param toFind a character sequence that may exist in the field
     * @return true if toFind exists in field
     */
    private fun checkContains(field: CharSequence?, toFind: CharSequence): Boolean {
        return field?.contains(toFind, true) ?: false
    }
}
