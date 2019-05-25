package com.therenegadecoder.poplibrary

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class BookActivity: AppCompatActivity() {

    private lateinit var titleView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_add_form)
        titleView = findViewById(R.id.book_title_input_form)

        val submitButton = findViewById<Button>(R.id.book_submit_button)
        submitButton.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(titleView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val title = titleView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, title)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    fun loadMainActivity(view: View) {
        val intent: Intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}