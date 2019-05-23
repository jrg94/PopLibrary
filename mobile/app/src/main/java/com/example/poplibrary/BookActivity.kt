package com.example.poplibrary

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class BookActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_add_form)
    }

    fun loadMainActivity(view: View) {
        val intent: Intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }
}