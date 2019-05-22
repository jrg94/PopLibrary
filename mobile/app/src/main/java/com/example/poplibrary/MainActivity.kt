package com.example.poplibrary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val book = Book("174-44-3221")
        book.pageCount = 100

        val textView = findViewById<TextView>(R.id.textView).apply {
            text = book.pageCount.toString()
        }
    }
}
