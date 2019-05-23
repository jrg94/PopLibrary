package com.example.poplibrary

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.book_list_item.view.*
import android.widget.Filter
import android.widget.Filterable
import kotlin.collections.ArrayList


class BookAdapter (private val books: List<Book>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>(), Filterable {

    private var booksSearchList: List<Book> = books

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
        holder.isbnTextView.text = booksSearchList[position].isbn13
        holder.titleTextView.text = booksSearchList[position].title
        holder.authorTextView.text = booksSearchList[position].author
        holder.coverImage.setImageBitmap(booksSearchList[position].coverImage)
    }

    override fun getItemCount() = booksSearchList.size

    /**
     * Filters the adapter to only include items that match the filter.
     */
    override fun getFilter(): Filter {
        return object: Filter() {

            /**
             * Performs the filtering operation based on a character sequence.
             */
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                if (charString.isEmpty()) {
                    booksSearchList = books
                } else {
                    val filteredList = ArrayList<Book>()
                    for (book in books) {
                        if (book.match(charString)) {
                            filteredList.add(book)
                        }
                    }
                    booksSearchList = filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = booksSearchList
                return filterResults
            }

            /**
             * Updates the bookSearchList to include only items that were filtered during search.
             */
            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                @Suppress("UNCHECKED_CAST")
                booksSearchList = results.values as ArrayList<Book>
                notifyDataSetChanged()
            }
        }
    }

    fun sort(key: String) {
        when (key) {
            "Title" -> booksSearchList = booksSearchList.sortedBy { it.title }
            "Page Count (Lowest First)" -> booksSearchList = booksSearchList.sortedBy { it.pageCount }
            "Author" -> booksSearchList = booksSearchList.sortedBy { it.author }
        }
        notifyDataSetChanged()
    }
}