package com.example.poplibrary

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.book_list_item.view.*
import android.graphics.drawable.Drawable
import android.util.Log
import java.io.InputStream
import java.lang.Exception
import java.net.URL


class BookAdapter (private val books: Array<Book>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    class BookViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val isbnTextView: TextView = view.isbn_text_view
        val titleTextView: TextView = view.title_text_view
        val authorTextView: TextView = view.author_text_view
        val coverImage: ImageView = view.cover_image_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.BookViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_list_item, parent, false) as View

        return BookViewHolder(textView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.isbnTextView.text = books[position].isbn13
        holder.titleTextView.text = books[position].title
        holder.authorTextView.text = books[position].author
        holder.coverImage.setImageBitmap(ImageLoader().execute(books[position].coverImageURL).get())
    }

    override fun getItemCount() = books.size

}