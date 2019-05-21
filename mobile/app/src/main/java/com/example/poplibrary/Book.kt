package com.example.poplibrary

import java.util.*

class Book(val ISBN: String) {
    var title: String = ""
    var pageCount: Int = -1
    var language: String = ""
    var editor: String = ""
    var dateOfPublication: Date = Date()
}
