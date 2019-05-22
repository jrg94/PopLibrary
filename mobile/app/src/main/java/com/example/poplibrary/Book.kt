package com.example.poplibrary

import java.util.*

class Book(
    val isbn: String? = null,
    val title: String? = null,
    val author: String? = null,
    val editor: String? = null,
    val language: String? = null,
    val coverImageURL: String? = null,
    val pageCount: Int? = null,
    val dateOfPublication: Date? = null
) { }
