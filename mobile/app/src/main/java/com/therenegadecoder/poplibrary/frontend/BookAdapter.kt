package com.therenegadecoder.poplibrary.frontend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.therenegadecoder.poplibrary.R
import com.therenegadecoder.poplibrary.data.Book
import kotlinx.android.synthetic.main.book_list_item.view.*


class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>(), Filterable {

    private var books = emptyList<Book>()
    private var booksSearchList: MutableList<Book> = books.toMutableList()

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val isbnTextView: TextView = view.isbn_text_view
        val titleTextView: TextView = view.title_text_view
        val authorTextView: TextView = view.author_text_view
        val coverImage: ImageView = view.cover_image_view
        val lexileLevelTextView: TextView = view.lexile_text_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_list_item, parent, false) as View

        return BookViewHolder(textView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.isbnTextView.text = booksSearchList[position].isbn13
        holder.titleTextView.text = booksSearchList[position].title
        holder.authorTextView.text = booksSearchList[position].author
        holder.lexileLevelTextView.text = booksSearchList[position].lexileLevel?.toString()
        Glide.with(holder.itemView)
            .load(booksSearchList[position].coverImageURL)
            .fallback(R.drawable.generic_book_cover)
            .into(holder.coverImage)
    }

    override fun getItemCount() = booksSearchList.size

    /**
     * Filters the adapter to only include items that match the filter.
     */
    override fun getFilter(): Filter {
        return object : Filter() {

            /**
             * Performs the filtering operation based on a character sequence.
             */
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                if (charString.isEmpty()) {
                    booksSearchList = books.toMutableList()
                } else {
                    val filteredList = ArrayList<Book>()
                    for (book in books) {
                        if (book.match(charString)) {
                            filteredList.add(book)
                        }
                    }
                    booksSearchList = filteredList
                }

                val filterResults = FilterResults()
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
            "Title" -> booksSearchList.sortBy { it.title }
            "Page Count (Lowest First)" -> booksSearchList.sortBy { it.pageCount }
            "Author" -> booksSearchList.sortBy { it.author }
            "Publication Date" -> booksSearchList.sortedBy { it.dateOfPublication }
            "Language" -> booksSearchList.sortBy { it.language }
        }
        notifyDataSetChanged()
    }

    fun setBooks(books: List<Book>) {
        this.books = books.toMutableList()
        this.booksSearchList = books.toMutableList()
        notifyDataSetChanged()
    }
}