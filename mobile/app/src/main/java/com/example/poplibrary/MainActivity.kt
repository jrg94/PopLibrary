package com.example.poplibrary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val books = generateBooks()

        viewManager = LinearLayoutManager(this)
        viewAdapter = BookAdapter(books)

        recyclerView = findViewById<RecyclerView>(R.id.book_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
        //recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
    }

    /**
     * Generates an array of books.
     */
    private fun generateBooks(): Array<Book> {
        return arrayOf(
            Book(isbn13 = "978-0743273565", title = "The Great Gatsby", pageCount = 180, editor = "Scribner", author = "F. Scott Fitzgerald", coverImageURL = "http://ecx.images-amazon.com/images/I/41eiFf1x23L._SL160_.jpg"),
            Book(isbn13 = "978-0143039433", title = "The Grapes of Wrath", pageCount = 464, editor = "Penguin Classics", author = "John Steinbeck", coverImageURL = "http://ecx.images-amazon.com/images/I/41adOkkXUzL._SL160_.jpg"),
            Book(isbn13 = "978-0452262935", title = "Nineteen Eighty-Four", pageCount = 304, editor = "Berkley", author = "George Orwell", coverImageURL = "http://ecx.images-amazon.com/images/I/41Kv1qGuXUL._SL160_.jpg"),
            Book(isbn13 = "978-1613823590", title = "Ulysses", pageCount = 564, editor = "Simon & Brown", author = "James Joyce", coverImageURL = "http://ecx.images-amazon.com/images/I/51XEH13NOnL._SL200_.jpg")
        )
    }
}
