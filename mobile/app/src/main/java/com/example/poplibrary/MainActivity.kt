package com.example.poplibrary

import android.app.SearchManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.Spinner

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: BookAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val books = generateBooks()

        viewManager = LinearLayoutManager(this)
        viewAdapter = BookAdapter(books.toList())

        recyclerView = findViewById<RecyclerView>(R.id.book_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = findViewById(R.id.book_search)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE

        // listening to search query text change
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                viewAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                viewAdapter.filter.filter(query)
                return false
            }
        })

        val spinner: Spinner = findViewById(R.id.sort_drop_down)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewAdapter.sort(parent?.getItemAtPosition(position).toString())
            }

        }
    }

    /**
     * Generates an array of books.
     */
    private fun generateBooks(): Array<Book> {
        return arrayOf(
            Book(isbn13 = "978-0743273565", title = "The Great Gatsby", pageCount = 180, editor = "Scribner", author = "F. Scott Fitzgerald", coverImageURL = "http://ecx.images-amazon.com/images/I/41eiFf1x23L._SL160_.jpg"),
            Book(isbn13 = "978-0143039433", title = "The Grapes of Wrath", pageCount = 464, editor = "Penguin Classics", author = "John Steinbeck", coverImageURL = "http://ecx.images-amazon.com/images/I/41adOkkXUzL._SL160_.jpg"),
            Book(isbn13 = "978-0452262935", title = "Nineteen Eighty-Four", pageCount = 304, editor = "Berkley", author = "George Orwell", coverImageURL = "http://ecx.images-amazon.com/images/I/41Kv1qGuXUL._SL160_.jpg"),
            Book(isbn13 = "978-1613823590", title = "Ulysses", pageCount = 564, editor = "Simon & Brown", author = "James Joyce", coverImageURL = "http://ecx.images-amazon.com/images/I/51XEH13NOnL._SL200_.jpg"),
            Book(isbn13 = "978-0316769174", title = "The Catcher in the Rye", pageCount = 277, editor = "Back Bay Books", author = "J. D. Salinger", coverImageURL = "http://ecx.images-amazon.com/images/I/511BDFArolL._SL200_.jpg"),
            Book(isbn13 = "978-0061743528", title = "To Kill a Mockingbird", pageCount = 323, editor = "Harper", author = "Harper Lee", coverImageURL = "http://ecx.images-amazon.com/images/I/51KFyfyK7eL._SL200_.jpg"),
            Book(isbn13 = "978-0544003415", title = "The Lord of the Rings", pageCount = 1285, editor = "Mariner Books", author = "J. R. R. Tolkien", coverImageURL = "http://ecx.images-amazon.com/images/I/516GyHY9p6L._SL200_.jpg"),
            Book(isbn13 = "978-0061767647", title = "Brave New World", pageCount = 384, editor = "Harper", author = "Aldous Huxley", coverImageURL = "http://ecx.images-amazon.com/images/I/41kwa0ECKKL._SL200_.jpg")
        )
    }
}
